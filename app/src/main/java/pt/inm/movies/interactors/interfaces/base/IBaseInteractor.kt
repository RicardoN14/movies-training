package pt.inm.movies.interactors.interfaces.base

import pt.inm.movies.interactors.listeners.base.IInteractorListener

interface IBaseInteractor<IL : IInteractorListener> {

    var requestContextGroup: String?
    var interactorListener: IL

    fun getRequestContext() = toString()

    fun cancelAllRunningRequests()
    fun retryFailedRequests()

}