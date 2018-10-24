package pt.inm.movies.entities.requests

import pt.inm.movies.http.entities.responses.HeaderResponseData

//import pt.inm.banka.webrequests.entities.responses.HeaderResponseData

/**
 * Created by ricardo.neves on 3/28/2018.
 */
class RequestError(val requestErrorType: RequestErrorType,
                   val headerResponseData: HeaderResponseData? = null,
                   val responseCode: Int = 0)