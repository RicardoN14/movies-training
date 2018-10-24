package pt.inm.movies.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_execute_request.*
import kotlinx.android.synthetic.main.loader.*
import pt.inm.movies.extensions.gone
import pt.inm.movies.extensions.visible
import pt.inm.movies.R
import pt.inm.movies.presenters.listeners.base.IPresenterListener
import pt.inm.movies.ui.screens.Screen
import pt.inm.movies.alias.PresentersArrayList
import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.entities.requests.RequestError
import pt.inm.movies.helpers.handleError
import pt.inm.movies.managers.ErrorStateManager
import pt.inm.webrequests.utils.DLog

abstract class ExecuteRequestFragment<S : Screen> : BaseFragment<S>(), IPresenterListener {

    private lateinit var presenters: PresentersArrayList
    private lateinit var implementationRootView: View

    private val errorStateManager by lazy {
        ErrorStateManager(screen, fragmentExecuteRequestErrorStateView) { onRetryButtonClicked() }
    }

    protected abstract fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String)

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView = inflater.inflate(R.layout.fragment_execute_request, container, false) as ViewGroup

        implementationRootView = LayoutInflater.from(screen).inflate(getLayoutResourceId(),
                rootView, false)

        rootView.addView(implementationRootView, 0)

        presenters = ArrayList()
        initPresenters(presenters, getRequestContextGroup())

        return rootView
    }

    protected open fun getRequestContextGroup() = toString()

    override fun showLoader() {
        loader?.visible()
    }

    override fun hideLoader() {
        loader?.gone()
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
        DLog.e(logTag, "requestConfig = $requestConfig requestError = $requestError")
        handleError(screen, requestConfig, requestError, errorStateManager)
    }

    override fun onDestroyView() {
        cancelAllRunningRequests()
        super.onDestroyView()
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

    override fun hideRootView() {
        implementationRootView.gone()
        fragmentExecuteRequestErrorStateView.gone()
    }

    override fun showRootView() {
        implementationRootView.visible()
        if (errorStateManager.isShowingError) {
            fragmentExecuteRequestErrorStateView.visible()
        }
    }

}
