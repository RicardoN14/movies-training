package pt.inm.movies.interactors.server.movies

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.callbacks.MoviesTrainingRequestListener
import pt.inm.movies.http.entities.qstringargs.NowPlayingMoviesQueryStringArgs
import pt.inm.movies.http.entities.responses.MoviesResponseData
import pt.inm.movies.interactors.interfaces.movies.IMoviesInteractor
import pt.inm.movies.interactors.listeners.movies.IMoviesInteractorListener
import pt.inm.movies.interactors.server.base.ServerInteractor

class MoviesServerInteractor : ServerInteractor<IMoviesInteractorListener>(), IMoviesInteractor {

    companion object {
        /** Web Request Id's **/
        const val NOW_PLAYING_MOVIES_WEB_REQUEST_ID = 1
    }

    /** Web Requests **/
    private lateinit var getNowPlayingMoviesWebRequest: MoviesTrainingWebRequest

    override fun createWebRequests(requestContextGroup: String) {
        getNowPlayingMoviesWebRequest = MoviesTrainingWebRequest(getWebRequestContext(),
                requestContextGroup, NOW_PLAYING_MOVIES_WEB_REQUEST_ID)
    }

    override fun requestNowPlayingMovies(queryStringArgs: NowPlayingMoviesQueryStringArgs,
                               requestConfig: RequestConfig?) {

        trySetRequestConfig(requestConfig, getNowPlayingMoviesWebRequest)

        val requestBuilder = webRequestsContainer.getMoviesWebRequests()
                .requestNowPlayingMovies(this, getNowPlayingMoviesWebRequest, queryStringArgs,
                        object : MoviesTrainingRequestListener<MoviesResponseData>() {
                            override fun onRequestSuccess(moviesResponse: MoviesResponseData) {
                                interactorListener.onRequestNowPlayingMoviesSuccess(moviesResponse)
                            }
                        })

        addRequestToRetryList(getNowPlayingMoviesWebRequest, requestBuilder)
    }

}