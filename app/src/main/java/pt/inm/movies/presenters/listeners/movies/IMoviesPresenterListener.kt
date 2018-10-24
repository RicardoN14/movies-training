package pt.inm.movies.presenters.listeners.movies

import pt.inm.movies.http.entities.responses.MoviesResponseData
import pt.inm.movies.presenters.listeners.base.IPresenterListener

interface IMoviesPresenterListener : IPresenterListener {
    fun onRequestNowPlayingMoviesSuccess(moviesResponseData: MoviesResponseData)
}