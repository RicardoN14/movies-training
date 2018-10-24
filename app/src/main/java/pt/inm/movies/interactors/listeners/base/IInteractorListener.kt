package pt.inm.movies.interactors.listeners.base

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.entities.requests.RequestError

interface IInteractorListener {
    fun showLoader()
    fun hideLoader()
    fun showRootView()
    fun hideRootView()
    fun showError(requestConfig: RequestConfig, requestError: RequestError)
}