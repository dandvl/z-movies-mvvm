package mx.devlabs.zmovies.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import mx.devlabs.zmovies.R
import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.services.Config

class MovieAdapter(var mOnMovieListener: OnMovieListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mMovies: List<Movie> = mutableListOf()

    companion object {
        private const val MOVIE_TYPE = 1
        private const val LOADING_TYPE = 2
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RecyclerView.ViewHolder {
        var view: View

        return when (i) {
            MOVIE_TYPE -> {
                view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_movie_list_item, viewGroup, false)
                MovieViewHolder(view, mOnMovieListener)
            }
            LOADING_TYPE -> {
                view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_loading_list_item, viewGroup, false)
                LoadingViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_movie_list_item, viewGroup, false)
                MovieViewHolder(view, mOnMovieListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
        if (getItemViewType(i) == MOVIE_TYPE) {
            viewHolder as MovieViewHolder
            viewHolder.title.text = mMovies[i].original_title
            viewHolder.publisher.text = mMovies[i].overview
            viewHolder.counter.text = (i + 1).toString()
            var requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_background)

            Glide
                    .with(viewHolder.itemView.context)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Config.IMG_HOST + mMovies[i].poster_path)
                    .into(viewHolder.image)
        }
    }

    fun setMovies(movies: List<Movie>) {
        this.mMovies = movies
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (mMovies[position].original_title.equals("LOADING...")) {
            LOADING_TYPE
        } else if (position == mMovies.size - 1
                && position != 0
                && !mMovies[position].original_title.equals("EXHAUSTED...")) {
            LOADING_TYPE
        } else {
            MOVIE_TYPE
        }
    }

    fun displayLoading() {
        if (!isLoading()) {
            val movie = Movie("LOADING...")
            val loadingList = mutableListOf<Movie>()
            loadingList.add(movie)
            mMovies = loadingList
            notifyDataSetChanged()
        }
    }

    private fun isLoading(): Boolean {
        if (mMovies.isNotEmpty()) {
            if (mMovies[mMovies.size].original_title.equals("LOADING...")) {
                return true
            }
        }
        return false
    }

    fun getSelectedMovie(position: Int): Movie {
        if (mMovies.isNotEmpty()) {
            return mMovies.get(position)
        }
        return Movie("¿¿??")
    }

}


