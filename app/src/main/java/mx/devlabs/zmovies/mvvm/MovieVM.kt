package mx.devlabs.zmovies.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import mx.devlabs.zmovies.models.Movie

class MovieVM : ViewModel() {

    private val mMovieRepository: MoviesRepository = MoviesRepository.getInstance()
    private var movieId = ""

    fun findById(id : String) {
        this.movieId = id
        return mMovieRepository.findById(id)
    }

    fun movie() : LiveData<Movie>{
        return mMovieRepository.movieLD
    }

}