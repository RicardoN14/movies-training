package pt.inm.movies.presenters.implementations.movies

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.movies.http.entities.qstringargs.NowPlayingMoviesQueryStringArgs
import pt.inm.movies.http.entities.responses.MoviesResponseData
import pt.inm.movies.injections.interactors.InteractorComponent
import pt.inm.movies.interactors.interfaces.movies.IMoviesInteractor
import pt.inm.movies.interactors.listeners.movies.IMoviesInteractorListener
import pt.inm.movies.presenters.implementations.base.BasePresenter
import pt.inm.movies.presenters.interfaces.movies.IMoviesPresenter
import pt.inm.movies.presenters.listeners.movies.IMoviesPresenterListener

class MoviesPresenter(presenterListener: IMoviesPresenterListener,
                      requestContextGroup: String)
    : BasePresenter<IMoviesPresenterListener, IMoviesInteractorListener, IMoviesInteractor>(
        presenterListener,
        requestContextGroup), IMoviesPresenter, IMoviesInteractorListener {

    override fun requestNowPlayingMovies(queryStringArgs: NowPlayingMoviesQueryStringArgs,
                                         requestConfig: RequestConfig?) {
        interactor.requestNowPlayingMovies(queryStringArgs, requestConfig)
    }

    override fun onRequestNowPlayingMoviesSuccess(moviesResponseData: MoviesResponseData) {
        presenterListener.onRequestNowPlayingMoviesSuccess(moviesResponseData)
    }

    // Todo Implement  your Methods

    override fun injectDependencies(component: InteractorComponent) {
        component.inject(this)
    }

    override fun getInteractorListener() = this

}