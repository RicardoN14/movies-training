package pt.inm.movies.http.interfaces

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.entities.requests.RequestError
import pt.inm.webrequests.RequestManager

interface IMoviesTrainingWebRequestContext : RequestManager.WebRequestContext {
    fun showLoader()
    fun hideLoader()
    fun showRootView()
    fun hideRootView()
    fun showError(requestConfig: RequestConfig, requestError: RequestError)
}