package pt.inm.movies.interactors.mocks.movies

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.http.entities.qstringargs.NowPlayingMoviesQueryStringArgs
import pt.inm.movies.interactors.interfaces.movies.IMoviesInteractor
import pt.inm.movies.interactors.listeners.movies.IMoviesInteractorListener
import pt.inm.movies.interactors.mocks.base.MockInteractor

class MoviesMockInteractor : MockInteractor<IMoviesInteractorListener>(), IMoviesInteractor {

    override fun requestNowPlayingMovies(queryStringArgs: NowPlayingMoviesQueryStringArgs,
                                         requestConfig: RequestConfig?) {
        // TODO
    }
}