package mx.devlabs.zmovies.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import mx.devlabs.zmovies.models.Movie

class MoviesRepository private constructor() {

    private var moviesLD: MutableLiveData<List<Movie>> = MutableLiveData()
    var movieLD : MutableLiveData<Movie> = MutableLiveData()

    //To save the state of the scroll of the recycler view
    private lateinit var query : String
    private var pageNumber: Int = 0

    fun getMovies() : LiveData<List<Movie>>
    {
        return moviesLD
    }

    fun searchMovies(query: String, pageNumber: Int) {
        this.query = query
        this.pageNumber = pageNumber

        MoviesServices.search(moviesLD, query, pageNumber)
    }

    fun searchNextPage(){
        searchMovies(query, ++pageNumber)
    }

    fun findById(id : String){
        MoviesServices.getMovieDetails(movieLD, id)
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




