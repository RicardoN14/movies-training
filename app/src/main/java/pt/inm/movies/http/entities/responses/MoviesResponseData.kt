package pt.inm.movies.http.entities.responses

import com.google.gson.annotations.SerializedName

data class MoviesResponseData(
        @SerializedName("results") val results: List<MovieResponseData>,
        @SerializedName("page") val page: Int?,
        @SerializedName("total_results") val totalResults: Int?,
        @SerializedName("total_pages") val totalPages: Int?
)