package pt.inm.movies.http.callbacks

import pt.inm.movies.alias.MoviesTrainingRequestManager
import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.interfaces.IMoviesTrainingWebRequestContext
import pt.inm.webrequests.callbacks.HandleRequestCallbacks
import pt.inm.movies.http.entities.responses.HeaderResponseData
import pt.inm.webrequests.utils.DLog

class MoviesTrainingHandleRequestCallbacks(private val requestManager: MoviesTrainingRequestManager)
    : HandleRequestCallbacks<IMoviesTrainingWebRequestContext,
        MoviesTrainingWebRequest, HeaderResponseData>() {

    private val logTag: String = javaClass.simpleName

    override fun onRequestStart(webRequestContext: IMoviesTrainingWebRequestContext, webRequest: MoviesTrainingWebRequest) {
        DLog.d(logTag, String.format("onRequestStart context = [ %s ] request = [ %s ]", webRequestContext, webRequest))
        val requestConfig = webRequest.requestConfig

        if (requestConfig.showLoader) {
            webRequestContext.showLoader()
        }

        if (requestConfig.hideRootView) {
            webRequestContext.hideRootView()
        }
    }

    override fun onRequestFinish(webRequestContext: IMoviesTrainingWebRequestContext, webRequest: MoviesTrainingWebRequest) {
        DLog.d(logTag, String.format("onRequestFinish context = [ %s ] request = [ %s ]", webRequestContext, webRequest))
        tryDismissLoader(webRequestContext, webRequest)
    }

    private fun tryDismissLoader(webRequestContext: IMoviesTrainingWebRequestContext, webRequest: MoviesTrainingWebRequest) {
        if (webRequest.requestConfig.showLoader) {
            val webRequestManager = requestManager.webRequestManager

            var hideLoader = true
            var showRootView = true

            webRequestManager.getRunningWebRequestsInContextGroup(webRequest.contextGroup)
                    .forEach {
                        val requestConfig = it.requestConfig

                        if (requestConfig.showLoader) {
                            // There is at least one request running that need the loader, lets not dismiss it!
                            hideLoader = false
                        }

                        if (requestConfig.hideRootView) {
                            // There is at least one request running that need to hide root view, lets not show it!
                            showRootView = false
                        }

                        if (!showRootView && !hideLoader) {
                            return
                        }
                    }

            if (hideLoader) {
                webRequestContext.hideLoader()
            }

            if (showRootView) {
                webRequestContext.showRootView()
            }
        }
    }

}

