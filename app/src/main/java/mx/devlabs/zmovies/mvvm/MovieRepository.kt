package mx.devlabs.zmovies.mvvm

import android.arch.lifecycle.LiveData
import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.requests.MovieApiClient

class MovieRepository private constructor() {

    private val mMovieApiClient: MovieApiClient = MovieApiClient.getInstance()

    fun getMovies() : LiveData<List<Movie>>
    {
        return mMovieApiClient.movies
    }

    fun searchMovies(query: String, pageNumber: Int) {
        var pageNumber = pageNumber
        if (pageNumber == 0) {
            pageNumber = 1
        }
        mMovieApiClient.searchMovies(query, pageNumber)
    }

    fun popularMovies() {
//        mMovieApiClient.getAllMoviesApi()
    }

    companion object {
        private var instance: MovieRepository ?= null

        fun getInstance(): MovieRepository {
            return (instance ?: MovieRepository())
        }
    }

    fun cancelRequest(){
        mMovieApiClient.cancelRequest()
    }
}




