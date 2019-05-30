package mx.devlabs.zmovies.testing

import android.util.Log
import mx.devlabs.zmovies.models.Movie

object Testing {

    fun printMovies(tag: String, list: List<Movie>) {
        for (movie in list) {
            Log.d(tag, "Movie: $movie.original_title")
        }
    }
}
