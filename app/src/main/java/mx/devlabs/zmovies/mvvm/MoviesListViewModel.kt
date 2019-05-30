package mx.devlabs.zmovies.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mx.devlabs.zmovies.models.Movie

class MoviesListViewModel : ViewModel() {

    private val mMovieRepository: MovieRepository = MovieRepository.getInstance()
    var isPerfomingQuery : Boolean = false

    val movies: LiveData<List<Movie>>
        get() = mMovieRepository.getMovies()

    fun searchMoviesApi(query: String, pageNumber: Int) {
        this.isPerfomingQuery = true
        mMovieRepository.searchMoviesWS(query, pageNumber)
    }

    fun getAllMoviesApi() {
        mMovieRepository.getMovies()
    }

    fun onBackPressed() : Boolean {
        if(isPerfomingQuery){
            mMovieRepository.cancelRequest()
            isPerfomingQuery = false
        }
        return true
    }
}