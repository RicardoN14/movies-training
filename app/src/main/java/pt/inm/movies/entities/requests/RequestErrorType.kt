package pt.inm.movies.entities.requests

/**
 * Created by ricardo.neves on 3/28/2018.
 */
enum class RequestErrorType {
    INTERNET_CONNECTION_ERROR,
    TIMEOUT_ERROR,
    SERVER_ERROR,
    PARSE_ERROR,
    SSL_ERROR
}