package mx.devlabs.zmovies.mvvm

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.services.HttpClient
import mx.devlabs.zmovies.services.HttpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.security.auth.login.LoginException

class MoviesServices{

    companion object {

        fun search(moviesLD : MutableLiveData<List<Movie>>, query : String, page : Int){

            val request = HttpClient.moviesRoutes.searchMovies("en-US", query, page, false)

            request.enqueue(object: Callback<HttpResponse<Movie>> {
                override fun onFailure(call: Call<HttpResponse<Movie>>, t: Throwable) {
                    Log.e("RMC", t.message)
                    moviesLD.postValue(null)
                }
                override fun onResponse(call: Call<HttpResponse<Movie>>, response: Response<HttpResponse<Movie>>) {
                    val response = response?.body() as HttpResponse
                    val movies = response.results

                    if(page == 1){
                        moviesLD.postValue(movies)
                    }else{
                        val currentMovies = moviesLD.value as MutableList<Movie>
                        currentMovies.addAll(movies)
                        moviesLD.postValue(currentMovies)
                    }
                }
            })

        }

        fun getMovieDetails(movieLD : MutableLiveData<Movie>, movieId : String){

            val request = HttpClient.moviesRoutes.movieDetail(movieId)

            request.enqueue(object: Callback<Movie> {
                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Log.i("RMC", "onFailure")
                }
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    Log.i("RMC", "onResponse")
                    val response = response?.body() as  Movie
                    movieLD.postValue(response)
                }
            })

        }


    }

}