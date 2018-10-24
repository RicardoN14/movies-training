package pt.inm.movies.interactors.server.base

import android.util.SparseArray
import com.android.volley.DefaultRetryPolicy
import pt.inm.movies.MoviesTrainingApplication
import pt.inm.movies.BuildConfig
import pt.inm.movies.R
import pt.inm.movies.http.interfaces.IMoviesTrainingWebRequestContext
import pt.inm.movies.http.callbacks.MoviesTrainingHandleRequestCallbacks
import pt.inm.movies.http.callbacks.MoviesTrainingHandleRequestError
import pt.inm.movies.http.callbacks.MoviesTrainingResponseValidator
import pt.inm.movies.interactors.interfaces.base.IBaseInteractor
import pt.inm.movies.interactors.listeners.base.IInteractorListener
import pt.inm.movies.alias.MoviesTrainingRequestManager
import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.entities.requests.RequestError
import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.entities.responses.HeaderResponseData
import pt.inm.movies.http.WebRequestsContainer
import pt.inm.webrequests.IRequestBuilder
import pt.inm.webrequests.utils.DLog

abstract class ServerInteractor<IL : IInteractorListener> : IBaseInteractor<IL>,
        IMoviesTrainingWebRequestContext {

    companion object {
        private const val REQUEST_TIMEOUT = 1000 * 60
        private const val MAX_NUM_OF_REQUESTS_RETRY = 0
        private const val BACKOFF_MULTIPLIER = 1F

        private lateinit var requestManager: MoviesTrainingRequestManager

        lateinit var webRequestsContainer: WebRequestsContainer

        init {
            initWebRequests()
        }

        private fun initWebRequests() {
            val retryPolicy = DefaultRetryPolicy(REQUEST_TIMEOUT,
                    MAX_NUM_OF_REQUESTS_RETRY, BACKOFF_MULTIPLIER)

            requestManager = MoviesTrainingRequestManager()
            requestManager.init(MoviesTrainingResponseValidator(),
                    HeaderResponseData::class.java,
                    MoviesTrainingHandleRequestError(),
                    MoviesTrainingHandleRequestCallbacks(requestManager),
                    retryPolicy, MoviesTrainingApplication.instance, BuildConfig.DEBUG)

            val endpoint = MoviesTrainingApplication.getString(R.string.server_endpoint)

            webRequestsContainer = WebRequestsContainer(endpoint, requestManager)
        }
    }

    /** Properties **/

    protected val tag = javaClass.simpleName

    private val requestsToRetrySparseArray by lazy {
        SparseArray<IRequestBuilder<MoviesTrainingWebRequest>>()
    }

    override var requestContextGroup: String? = null
        set(value) {
            field = value
            if (field != null) {
                createWebRequests(field!!)
            }
        }

    override lateinit var interactorListener: IL

    /** Abstract Functions **/
    protected abstract fun createWebRequests(requestContextGroup: String)

    /** Open Functions **/
    protected open fun getWebRequestContext() = this.toString()

    override fun showLoader() {
        interactorListener.showLoader()
    }

    override fun hideLoader() {
        interactorListener.hideLoader()
    }

    override fun showRootView() {
        interactorListener.showRootView()
    }

    override fun hideRootView() {
        interactorListener.hideRootView()
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
        DLog.e(tag, "requestConfig = $requestConfig requestError = $requestError")
        interactorListener.showError(requestConfig, requestError)
    }

    protected fun addRequestToRetryList(webRequest: MoviesTrainingWebRequest,
                                        requestBuilder: IRequestBuilder<MoviesTrainingWebRequest>) {
        requestsToRetrySparseArray.put(webRequest.id, requestBuilder)
    }

    /** This method MUST be called before execute the request **/
    protected fun trySetRequestConfig(requestConfig: RequestConfig?, webRequest: MoviesTrainingWebRequest) {
        requestConfig?.let {
            webRequest.requestConfig = requestConfig
        }
    }

    override fun cancelAllRunningRequests() {
        val webRequestManager = requestManager.webRequestManager
        val webRequestContext = getWebRequestContext()
        webRequestManager.cancelAll(webRequestContext)
        webRequestManager.clearFailedWebRequests(webRequestContext)
        requestsToRetrySparseArray.clear()
    }

    override fun retryFailedRequests() {
        val webRequestManager = requestManager.webRequestManager
        val webRequestContext = getWebRequestContext()
        val failedWebRequest = webRequestManager.getFailedWebRequests(webRequestContext)

        /**
         * This is needed because the call to onRequestRetry will cause the request failed to
         * run again, and that will cause the call to onRemove on a list that we are iterating
         * this will throw the ConcurrentException. To avoid this we need to pass this to another list
         * **/

        if (failedWebRequest != null && !failedWebRequest.isEmpty()) {
            val webRequestToRetry = ArrayList<MoviesTrainingWebRequest>()

            failedWebRequest.forEach {
                webRequestToRetry.add(it)
            }

            webRequestToRetry.forEach {
                requestsToRetrySparseArray[it.id]?.startRequest()
            }

            //after retry clear all failed requests
            webRequestManager.clearFailedWebRequests(webRequestContext)
        }
    }

}