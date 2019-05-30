package mx.devlabs.zmovies.mvvm

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.services.HttpClient
import mx.devlabs.zmovies.services.HttpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesServices{

    companion object {

        fun search(moviesLD : MutableLiveData<List<Movie>>, query : String, page : Int){

            val request = HttpClient.moviesRoutes.searchMovies("en-US", query, page, false)

            request.enqueue(object: Callback<HttpResponse<Movie>> {
                override fun onFailure(call: Call<HttpResponse<Movie>>, t: Throwable) {
                    Log.e("RMC", t.message)
                }
                override fun onResponse(call: Call<HttpResponse<Movie>>, response: Response<HttpResponse<Movie>>) {
                    val response = response?.body() as HttpResponse
                    val movies = response.results

                    moviesLD.postValue(movies)
                }
            })

        }

    }

}