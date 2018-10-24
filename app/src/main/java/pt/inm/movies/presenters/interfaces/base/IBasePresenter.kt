package pt.inm.movies.presenters.interfaces.base

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.entities.requests.RequestError
import pt.inm.movies.interactors.interfaces.base.IBaseInteractor
import pt.inm.movies.interactors.listeners.base.IInteractorListener
import pt.inm.movies.presenters.listeners.base.IPresenterListener

interface IBasePresenter<out PL : IPresenterListener, IL : IInteractorListener,
        I : IBaseInteractor<IL>> : IInteractorListener {

    val presenterListener: PL
    val requestContextGroup: String
    var interactor: I

    override fun showLoader() {
        presenterListener.showLoader()
    }

    override fun hideLoader() {
        presenterListener.hideLoader()
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
        presenterListener.showError(requestConfig, requestError)
    }

    override fun showRootView() {
        presenterListener.showRootView()
    }

    override fun hideRootView() {
        presenterListener.hideRootView()
    }

    fun cancelAllRunningRequests() {
        interactor.cancelAllRunningRequests()
    }

    fun retryFailedRequests(){
        interactor.retryFailedRequests()
    }

}