package pt.inm.movies.http

import pt.inm.movies.entities.requests.RequestConfig
import pt.inm.webrequests.WebRequest

class MoviesTrainingWebRequest(context: String,
                               contextGroup: String, id: Int,
                               var requestConfig: RequestConfig = RequestConfig())
    : WebRequest(context, id, contextGroup)