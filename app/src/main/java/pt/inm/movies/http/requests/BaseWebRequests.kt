package pt.inm.movies.http.requests

import androidx.annotation.CallSuper
import com.android.volley.Request
import pt.inm.movies.MoviesTrainingApplication
import pt.inm.movies.R
import pt.inm.movies.extensions.addQueryParam
import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.WebRequestsContainer
import pt.inm.movies.http.callbacks.MoviesTrainingHandleRequestCallbacks
import pt.inm.movies.http.callbacks.MoviesTrainingHandleRequestError
import pt.inm.movies.http.callbacks.MoviesTrainingRequestListener
import pt.inm.movies.http.callbacks.MoviesTrainingResponseValidator
import pt.inm.movies.http.interfaces.IMoviesTrainingWebRequestContext
import pt.inm.webrequests.IRequestBuilder
import java.util.*

abstract class BaseWebRequests(private val endpoint: String, requestContainer: WebRequestsContainer) {

    companion object {
        const val API_KEY_QUERY_PARAM_KEY = "api_key"
    }

    protected val requestManager = requestContainer.requestManager

    /**
     * Headers to send in all requests by default
     */
    private val headers = HashMap<String, String>()

    init {
        // TODO Populate headers if needed
    }

    protected fun <R> startRequest(context: IMoviesTrainingWebRequestContext,
                                      webRequest: MoviesTrainingWebRequest,
                                      url: String,
                                      methodType: Int = Request.Method.GET,
                                      headers: HashMap<String, String> = this.headers,
                                      requestListener: MoviesTrainingRequestListener<R>? = null,
                                      responseEntityClass: Class<R>? = null,
                                      handleRequestError: MoviesTrainingHandleRequestError? = null,
                                      handleRequestCallbacks: MoviesTrainingHandleRequestCallbacks? = null,
                                      responseValidator: MoviesTrainingResponseValidator? = null): IRequestBuilder<MoviesTrainingWebRequest> {

        return startRequest(context, webRequest, url, methodType, headers, requestListener, responseEntityClass, handleRequestError,
                handleRequestCallbacks, responseValidator, null, Void::class.java)
    }

    protected fun <R, S> startRequest(context: IMoviesTrainingWebRequestContext,
                                      webRequest: MoviesTrainingWebRequest,
                                      url: String,
                                      methodType: Int = Request.Method.GET,
                                      headers: HashMap<String, String> = this.headers,
                                      requestListener: MoviesTrainingRequestListener<R>? = null,
                                      responseEntityClass: Class<R>? = null,
                                      handleRequestError: MoviesTrainingHandleRequestError? = null,
                                      handleRequestCallbacks: MoviesTrainingHandleRequestCallbacks? = null,
                                      responseValidator: MoviesTrainingResponseValidator? = null,
                                      requestEntity: S? = null,
                                      requestEntityClass: Class<S>? = null): IRequestBuilder<MoviesTrainingWebRequest> {

        val requestBuilder = requestManager.buildRequest(context, webRequest, url, methodType)
                .withHeaders(headers)
                .withHandleRequestError(handleRequestError)
                .withHandleRequestCallbacks(handleRequestCallbacks)
                .withResponseValidator(responseValidator)
                .withRequestListener(requestListener, responseEntityClass)
                .withRequestEntity(requestEntity, requestEntityClass)

        requestBuilder.startRequest()

        return requestBuilder
    }

    protected fun initUrl(vararg paths: String): StringBuilder {

        val sb = StringBuilder(endpoint)

        paths.forEach {
            sb.append("/").append(it)
        }

        return addCommonQueryParams(sb)
    }

    @CallSuper
    protected open fun addCommonQueryParams(sb: StringBuilder): StringBuilder{
        return sb.addQueryParam(API_KEY_QUERY_PARAM_KEY, MoviesTrainingApplication.getString(R.string.api_key))
    }

}