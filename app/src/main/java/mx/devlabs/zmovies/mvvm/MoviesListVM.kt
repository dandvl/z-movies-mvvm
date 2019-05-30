package mx.devlabs.zmovies.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mx.devlabs.zmovies.models.Movie

class MoviesListVM : ViewModel() {

    private val mMovieRepository: MovieRepository = MovieRepository.getInstance()
    var isPerformingQuery : Boolean = false

    val movies: LiveData<List<Movie>> get() = mMovieRepository.getMovies()

    fun searchMovies(query: String, pageNumber: Int) {
        this.isPerformingQuery = true
        mMovieRepository.searchMovies(query, pageNumber)
    }

    fun getAllMoviesApi() {
        mMovieRepository.getMovies()
    }

    fun onBackPressed() : Boolean {
        if(isPerformingQuery){
            mMovieRepository.cancelRequest()
            isPerformingQuery = false
        }
        return true
    }
}