package mx.devlabs.zmovies.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import mx.devlabs.zmovies.R
import mx.devlabs.zmovies.models.Movie

class MovieActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        getIncomingIntent()
    }

    private fun getIncomingIntent(){
        if(intent.hasExtra("movie")){
            var movie = intent.getParcelableExtra<Movie>("movie")
            Log.d("RMC", movie.id)
            Log.d("RMC", movie.backdrop_path)
            Log.d("RMC", movie.original_title)
            Log.d("RMC", movie.overview)
            Log.d("RMC", movie.poster_path)
        }
    }
}
