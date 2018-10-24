package pt.inm.movies.ui.screens

import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.loader.*
import kotlinx.android.synthetic.main.screen_execute_request.*
import pt.inm.movies.presenters.listeners.base.IPresenterListener
import pt.inm.movies.R
import pt.inm.movies.alias.PresentersArrayList
import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.entities.requests.RequestError
import pt.inm.movies.extensions.gone
import pt.inm.movies.extensions.visible
import pt.inm.movies.helpers.handleError
import pt.inm.movies.managers.ErrorStateManager
import pt.inm.webrequests.utils.DLog

abstract class ExecuteRequestScreen : Screen(), IPresenterListener {

    companion object {
        const val ERROR_DIALOG_ID = "ExecuteRequestScreen::ErrorDialogId"
    }

    private lateinit var implementationRootView: View
    private lateinit var presenters: PresentersArrayList

    /** Managers **/
    protected open val errorStateManager by lazy {
        ErrorStateManager(this, screenExecuteRequestErrorStateView,
                onRetryButtonClickedAction = { onRetryButtonClicked() })
    }

    protected abstract fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String)
    protected abstract fun getExecuteRequestLayoutResourceId(): Int
    protected abstract fun doExecuteRequestInitializations()

    final override fun doInitializations() {
        presenters = ArrayList()
        initPresenters(presenters, getRequestContextGroup())

        implementationRootView = LayoutInflater.from(this).inflate(getExecuteRequestLayoutResourceId(),
                screenExecuteRequestRootView, false)

        screenExecuteRequestRootView.addView(implementationRootView, 0)

        doExecuteRequestInitializations()
    }

    final override fun getLayoutResourceId() = R.layout.screen_execute_request

    protected open fun getRequestContextGroup() = toString()

    override fun showLoader() {
        loader?.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        loader?.visibility = View.GONE
    }

    private fun cancelAllRunningRequests() {
        presenters.forEach {
            it.cancelAllRunningRequests()
        }
    }

    private fun onRetryButtonClicked() {
        errorStateManager.hide()
        presenters.forEach {
            it.retryFailedRequests()
        }
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
        DLog.e(logTag, "requestConfig = $requestConfig requestError = $requestError")
        handleError(this, requestConfig, requestError, errorStateManager)
    }

    override fun hideRootView() {
        implementationRootView.gone()
        screenExecuteRequestErrorStateView.gone()
    }

    override fun showRootView() {
        implementationRootView.visible()
        if (errorStateManager.isShowingError) {
            screenExecuteRequestErrorStateView.visible()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelAllRunningRequests()
    }

}

