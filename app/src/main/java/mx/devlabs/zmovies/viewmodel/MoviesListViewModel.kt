package mx.devlabs.zmovies.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.repositories.MovieRepository

class MoviesListViewModel : ViewModel() {

    private val mMovieRepository: MovieRepository
    var isPerfomingQuery : Boolean = false

    val movies: LiveData<List<Movie>>
        get() = mMovieRepository.getMovies()

    init {
        mMovieRepository = MovieRepository.getInstance()
    }

    fun searchMoviesApi(query: String, pageNumber: Int) {
        this.isPerfomingQuery = true
        mMovieRepository.searchMoviesApi(query, pageNumber)
    }

    fun getAllMoviesApi() {
        mMovieRepository.getAllMoviesApi()
    }

    fun onBackPressed() : Boolean {
        if(isPerfomingQuery){
            mMovieRepository.cancelRequest()
            isPerfomingQuery = false
        }
        return true
    }
}