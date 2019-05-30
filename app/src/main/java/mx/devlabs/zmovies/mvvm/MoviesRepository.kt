package mx.devlabs.zmovies.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import mx.devlabs.zmovies.models.Movie

class MoviesRepository private constructor() {

    private var moviesLD: MutableLiveData<List<Movie>> = MutableLiveData()

    fun getMovies() : LiveData<List<Movie>>
    {
        return moviesLD
    }

    fun searchMovies(query: String, pageNumber: Int) {
        var pageNumber = pageNumber
        if (pageNumber == 0) {
            pageNumber = 1
        }

        MoviesServices.search(moviesLD, query, pageNumber)
    }

    fun popularMovies() {
//        mMovieApiClient.getAllMoviesApi()
    }

    companion object {
        private var instance: MoviesRepository ?= null

        fun getInstance(): MoviesRepository {
            return (instance ?: MoviesRepository())
        }
    }

    fun cancelRequest(){
//        mMovieApiClient.cancelRequest()
    }
}




