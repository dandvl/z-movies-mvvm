package mx.devlabs.zmovies.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_movie.*
import mx.devlabs.zmovies.R
import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.mvvm.MovieVM
import mx.devlabs.zmovies.services.Config

class MovieActivity : BaseActivity()  {


    private lateinit var movieVM : MovieVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        movieVM = ViewModelProviders.of(this).get(MovieVM::class.java)
        suscribeObservers()
        getIncomingIntent()
        showProgressBar(true)
    }

    private fun getIncomingIntent(){
        if(intent.hasExtra("movie")){
            var movie = intent.getParcelableExtra<Movie>("movie")
            movieVM.findById(movie.id)
        }
    }

    private fun suscribeObservers(){
        movieVM.movie().observe(this, Observer<Movie> { movie ->

            var requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)

            Glide
                    .with(this)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Config.IMG_HOST + movie?.poster_path)
                    .into(movie_image)

            Log.i("RMC", "movie  ${movie?.original_title}")
            Log.i("RMC", "movie  ${movie?.popularity}")
            Log.i("RMC", "movie  ${movie?.imdb_id}")

            showProgressBar(false)
            scrollView.visibility = View.VISIBLE

            movie_title.text = movie?.original_title
            movie_overview.text = movie?.overview
            movie?.popularity.let {
                movie_score.text = (Math.round(it!!)).toString()
            }
        })

    }
}
