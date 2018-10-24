package pt.inm.movies.http.callbacks

import pt.inm.movies.entities.requests.RequestError
import pt.inm.movies.entities.requests.RequestErrorType
import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.entities.responses.HeaderResponseData
import pt.inm.movies.http.interfaces.IMoviesTrainingWebRequestContext
import pt.inm.webrequests.callbacks.HandleRequestError
import pt.inm.webrequests.callbacks.ResponseError
import pt.inm.webrequests.utils.DLog
import javax.net.ssl.SSLException

class MoviesTrainingHandleRequestError : HandleRequestError<IMoviesTrainingWebRequestContext, MoviesTrainingWebRequest, HeaderResponseData>() {

    companion object {
        val TAG: String = MoviesTrainingHandleRequestError::class.java.simpleName
    }

    override fun onRequestError(webRequestContext: IMoviesTrainingWebRequestContext,
                                responseError: ResponseError<HeaderResponseData>,
                                webRequest: MoviesTrainingWebRequest) {
        DLog.d(TAG, "onRequestError()")
        val requestError = RequestError(RequestErrorType.SERVER_ERROR,
                responseError.responseHeader, responseError.responseCode)
        webRequestContext.showError(webRequest.requestConfig, requestError)
    }

    override fun onRequestTimeout(webRequestContext: IMoviesTrainingWebRequestContext,
                                  webRequest: MoviesTrainingWebRequest) {
        super.onRequestTimeout(webRequestContext, webRequest)
        DLog.d(TAG, "onRequestTimeout()")
        val requestError = RequestError(RequestErrorType.TIMEOUT_ERROR)
        webRequestContext.showError(webRequest.requestConfig, requestError)
    }

    override fun onInternetConnectionError(webRequestContext: IMoviesTrainingWebRequestContext,
                                           webRequest: MoviesTrainingWebRequest) {
        super.onInternetConnectionError(webRequestContext, webRequest)
        DLog.d(TAG, "onInternetConnectionError()")
        val requestError = RequestError(RequestErrorType.INTERNET_CONNECTION_ERROR)
        webRequestContext.showError(webRequest.requestConfig, requestError)
    }

    override fun onSSLError(webRequestContext: IMoviesTrainingWebRequestContext,
                            webRequest: MoviesTrainingWebRequest, ex: SSLException) {
        super.onSSLError(webRequestContext, webRequest, ex)
        DLog.e(TAG, "onSSLError() exception = $ex")
        val requestError = RequestError(RequestErrorType.SSL_ERROR)
        webRequestContext.showError(webRequest.requestConfig, requestError)
    }

    override fun onParseError(webRequestContext: IMoviesTrainingWebRequestContext,
                              webRequest: MoviesTrainingWebRequest, ex: Exception) {
        super.onParseError(webRequestContext, webRequest, ex)
        DLog.e(TAG, "onParseError() exception = $ex")
        val requestError = RequestError(RequestErrorType.PARSE_ERROR)
        webRequestContext.showError(webRequest.requestConfig, requestError)
    }

    override fun onChecksumError(webRequestContext: IMoviesTrainingWebRequestContext,
                                 webRequest: MoviesTrainingWebRequest) {
        super.onChecksumError(webRequestContext, webRequest)
        DLog.d(TAG, "onChecksumError()")

    }

    override fun onRequestAlreadyRunning(webRequestContext: IMoviesTrainingWebRequestContext,
                                         webRequest: MoviesTrainingWebRequest) {
        super.onRequestAlreadyRunning(webRequestContext, webRequest)
        DLog.d(TAG, "onRequestAlreadyRunning()")
    }

}

