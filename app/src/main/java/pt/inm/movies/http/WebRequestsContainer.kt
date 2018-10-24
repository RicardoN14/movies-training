package pt.inm.movies.http

import androidx.collection.ArrayMap
import pt.inm.movies.alias.MoviesTrainingRequestManager
import pt.inm.movies.http.requests.BaseWebRequests
import pt.inm.movies.http.requests.MoviesWebRequests

class WebRequestsContainer(private val endpoint: String,
                           val requestManager: MoviesTrainingRequestManager){

    companion object {

        private const val MOVIE_PATH_PART = "movie"

    }

    private val webRequestsMap = ArrayMap<String, BaseWebRequests>()

    init{
        createWebRequests()
    }

    private fun createWebRequests() {

        webRequestsMap.apply {
            put(MOVIE_PATH_PART, MoviesWebRequests(endpoint + MOVIE_PATH_PART, this@WebRequestsContainer))
        }

    }

    fun getMoviesWebRequests() = webRequestsMap[MOVIE_PATH_PART] as MoviesWebRequests

}