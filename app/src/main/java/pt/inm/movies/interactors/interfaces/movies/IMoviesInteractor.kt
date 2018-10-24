package pt.inm.movies.interactors.interfaces.movies

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.http.entities.qstringargs.NowPlayingMoviesQueryStringArgs
import pt.inm.movies.interactors.interfaces.base.IBaseInteractor
import pt.inm.movies.interactors.listeners.movies.IMoviesInteractorListener

interface IMoviesInteractor : IBaseInteractor<IMoviesInteractorListener> {
    fun requestNowPlayingMovies(queryStringArgs: NowPlayingMoviesQueryStringArgs,
                                requestConfig: RequestConfig? = null)
}