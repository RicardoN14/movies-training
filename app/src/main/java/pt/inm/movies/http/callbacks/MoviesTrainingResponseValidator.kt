package pt.inm.movies.http.callbacks

import pt.inm.movies.http.MoviesTrainingWebRequest
import pt.inm.movies.http.interfaces.IMoviesTrainingWebRequestContext
import pt.inm.webrequests.callbacks.ResponseValidator
import pt.inm.movies.http.entities.responses.HeaderResponseData

class MoviesTrainingResponseValidator : ResponseValidator<IMoviesTrainingWebRequestContext,
        MoviesTrainingWebRequest, HeaderResponseData>() {

    override fun getHeaderNameFromResponse() = null

    override fun getBodyNameFromResponse() = null

    override fun headerIsValid(webRequestContext: IMoviesTrainingWebRequestContext?,
                               headerResponse: HeaderResponseData?,
                               webRequest: MoviesTrainingWebRequest?): Boolean {
        return true
    }
}