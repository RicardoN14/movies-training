package pt.inm.movies.interactors.listeners.movies

import pt.inm.movies.http.entities.responses.MoviesResponseData
import pt.inm.movies.interactors.listeners.base.IInteractorListener

interface IMoviesInteractorListener : IInteractorListener {
    fun onRequestNowPlayingMoviesSuccess(moviesResponseData: MoviesResponseData)
}