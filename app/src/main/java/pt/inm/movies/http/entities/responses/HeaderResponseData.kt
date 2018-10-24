package pt.inm.movies.http.entities.responses

import com.google.gson.annotations.SerializedName

data class HeaderResponseData(@SerializedName("status_code") val statusCode: Int?,
                              @SerializedName("status_message") val statusMessage: String?,
                              val success: Boolean?)