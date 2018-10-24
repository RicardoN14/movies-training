package pt.inm.movies.http.requests

import com.android.volley.Request
import pt.inm.movies.extensions.addQueryParams
import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.WebRequestsContainer
import pt.inm.movies.http.callbacks.MoviesTrainingRequestListener
import pt.inm.movies.http.entities.qstringargs.NowPlayingMoviesQueryStringArgs
import pt.inm.movies.http.entities.responses.MoviesResponseData
import pt.inm.movies.http.interfaces.IMoviesTrainingWebRequestContext
import pt.inm.webrequests.IRequestBuilder

class MoviesWebRequests(endpoint: String, requestContainer: WebRequestsContainer)
    : BaseWebRequests(endpoint, requestContainer) {

    companion object {
        const val NOW_PLAYING_PATH_PART = "now_playing"
        const val POPULAR_PATH_PART = "popular"
    }

    fun requestNowPlayingMovies(context: IMoviesTrainingWebRequestContext,
                                webRequest: MoviesTrainingWebRequest,
                                queryStringArgs: NowPlayingMoviesQueryStringArgs,
                                requestListener: MoviesTrainingRequestListener<MoviesResponseData>):
            IRequestBuilder<MoviesTrainingWebRequest> {

        val url = initUrl(NOW_PLAYING_PATH_PART).addQueryParams(queryStringArgs).toString()

        return startRequest(context, webRequest, url, Request.Method.GET, requestListener = requestListener,
                responseEntityClass = MoviesResponseData::class.java)
    }

}