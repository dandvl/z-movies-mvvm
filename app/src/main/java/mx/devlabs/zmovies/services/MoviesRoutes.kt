package mx.devlabs.zmovies.services

import mx.devlabs.zmovies.models.Movie
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET

interface MoviesRoutes {

    @GET(Config.LAST_CHANGES)
    fun movies(@Query("api_key") api_key: String): Call<HttpResponse<Movie>>

    @GET(Config.LAST_CHANGES)
    fun lastMoviesIn(@Query("api_key") api_key: String, @Query("start_date") start_date: String, @Query("end_date") end_date: String): Call<HttpResponse<Movie>>

    @GET(Config.POPULAR_MOVIES)
    fun popularMovies(@Query("api_key") api_key: String): Call<HttpResponse<Movie>>

    @GET(Config.SEARCH_MOVIES)
    fun searchMovies(@Query("lang") lang : String, @Query("query") query : String, @Query("page") page : Int, @Query("include_adult") include_adult: Boolean): Call<HttpResponse<Movie>>

    @GET(Config.MOVIE_DETAIL)
    fun movieDetail(@Path("movie_id") movie_id: String): Call<Movie>

}