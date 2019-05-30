package mx.devlabs.zmovies.services

import mx.devlabs.zmovies.models.Movie
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET

interface WebServices {

    @GET(Routes.LAST_CHANGES)
    fun movies(@Query("api_key") api_key: String): Call<HttpResponse<Movie>>

    @GET(Routes.LAST_CHANGES)
    fun lastMoviesIn(@Query("api_key") api_key: String, @Query("start_date") start_date: String, @Query("end_date") end_date: String): Call<HttpResponse<Movie>>

    @GET(Routes.MOVIE_DETAIL)
    fun movieDetail(@Path("movie_id") movie_id: String, @Query("api_key") api_key: String): Call<Movie>

}