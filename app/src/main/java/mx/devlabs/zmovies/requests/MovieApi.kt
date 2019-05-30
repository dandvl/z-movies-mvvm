package mx.devlabs.zmovies.requests

import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.services.HttpResponse
import mx.devlabs.zmovies.services.Routes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    // SEARCH
//    @GET("api/search")
//    fun searchMovie(
//            @Query("key") key: String,
//            @Query("q") query: String,
//            @Query("page") page: String
//    ): Call<RecipeSearchResponse>
//
//    // GET SPECIFIC RECIPE
//    @GET("api/get")
//    fun getRecipe(
//            @Query("key") key: String,
//            @Query("rId") recipe_id: String
//    ): Call<RecipeResponse>

    @GET(Routes.LAST_CHANGES)
    fun movies(@Query("api_key") api_key: String): Call<HttpResponse<Movie>>

    @GET(Routes.LAST_CHANGES)
    fun lastMoviesIn(@Query("api_key") api_key: String, @Query("start_date") start_date: String, @Query("end_date") end_date: String): Call<HttpResponse<Movie>>

    @GET(Routes.MOVIE_DETAIL)
    fun movieDetail(@Path("movie_id") movie_id: String, @Query("api_key") api_key: String): Call<Movie>

    @GET(Routes.POPULAR_MOVIES)
    fun popularMovies(@Query("api_key") api_key: String): Call<HttpResponse<Movie>>

    @GET(Routes.SEARCH_MOVIES)
    fun searchMovies(@Query("lang") lang : String, @Query("query") query : String, @Query("page") page : Int, @Query("include_adult") include_adult: Boolean): Call<HttpResponse<Movie>>

}
