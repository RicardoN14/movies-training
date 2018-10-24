package pt.inm.movies.http.callbacks

import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.interfaces.IMoviesTrainingWebRequestContext
import pt.inm.webrequests.callbacks.RequestListener
import pt.inm.movies.http.entities.responses.HeaderResponseData

abstract class MoviesTrainingRequestListener<E> : RequestListener<IMoviesTrainingWebRequestContext,
        MoviesTrainingWebRequest, HeaderResponseData, E>()