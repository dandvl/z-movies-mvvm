package mx.devlabs.zmovies.adapters

import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import mx.devlabs.zmovies.R

class MovieViewHolder(var parent_view: View, var onMovieListener: OnMovieListener) : RecyclerView.ViewHolder(parent_view), View.OnClickListener{

    var title = parent_view.findViewById<View>(R.id.movie_title) as TextView
    var publisher : TextView = parent_view.findViewById<View>(R.id.movie_publisher) as TextView
    var image = parent_view.findViewById<View>(R.id.movie_image) as AppCompatImageView
    var counter : TextView = parent_view.findViewById<View>(R.id.movie_counter) as TextView

    init{
        parent_view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        onMovieListener.onMovieClick(adapterPosition)
    }

}