package pt.inm.movies.presenters.interfaces.movies

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.http.entities.qstringargs.NowPlayingMoviesQueryStringArgs
import pt.inm.movies.interactors.interfaces.movies.IMoviesInteractor
import pt.inm.movies.interactors.listeners.movies.IMoviesInteractorListener
import pt.inm.movies.presenters.interfaces.base.IBasePresenter
import pt.inm.movies.presenters.listeners.movies.IMoviesPresenterListener

interface IMoviesPresenter : IBasePresenter<IMoviesPresenterListener, IMoviesInteractorListener, IMoviesInteractor> {
    fun requestNowPlayingMovies(queryStringArgs: NowPlayingMoviesQueryStringArgs,
                      requestConfig: RequestConfig? = null)
}