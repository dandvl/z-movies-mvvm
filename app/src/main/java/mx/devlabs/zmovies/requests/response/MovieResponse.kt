package mx.devlabs.zmovies.requests.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse {

    @Expose
    @SerializedName("id")
    lateinit var id: String

    @Expose
    @SerializedName("adult")
    var adult: Boolean = false

    @Expose
    @SerializedName("original_title")
    lateinit var original_title: String

    @Expose
    @SerializedName("poster_path")
    lateinit var poster_path : String

    @Expose
    @SerializedName("backdrop_path")
    lateinit var backdrop_path : String

    @Expose
    @SerializedName("overview")
    lateinit var overview: String

    override fun toString(): String {
        return """RecipeResponse{recipe=$original_title'}'.toString()"""
    }
}
