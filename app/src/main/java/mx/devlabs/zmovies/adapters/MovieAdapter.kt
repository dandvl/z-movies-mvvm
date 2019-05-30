package mx.devlabs.zmovies.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import mx.devlabs.zmovies.R
import mx.devlabs.zmovies.models.Movie
import com.bumptech.glide.Glide
import mx.devlabs.zmovies.services.HttpClient
import mx.devlabs.zmovies.services.Routes
import mx.devlabs.zmovies.services.WebServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieAdapter(private val moviesData : List<Movie>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>()  {

    private val MAX_CHARS_TITLE = 10

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.single_movie, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return moviesData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val services = HttpClient.instance().create(WebServices::class.java)
        val movie = services.movieDetail(moviesData[position].id, Routes.API_KEY)

        movie.enqueue(object: Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("zmovies", t.message)
            }
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                //Log.i("zmovies", "URL:" + response.raw().request().url()+"")
                val movieDetail = response.body()

                var shorTitle = movieDetail?.original_title
                var spaceAfterMax = movieDetail?.original_title?.indexOf(" ", MAX_CHARS_TITLE)

                if(spaceAfterMax != null && spaceAfterMax > 0) {
                    shorTitle = movieDetail?.original_title?.substring(0, spaceAfterMax) + "..."
                }

                holder.txt_title.text = shorTitle

                Glide
                    .with(holder.img_cover.context)
                    .load(Routes.IMG_HOST + movieDetail?.poster_path)
                    .centerCrop()
                    .placeholder(R.drawable.default_image)
                    .into(holder.img_cover)
            }
        })

    }

    class ViewHolder(val parent_view: View) : RecyclerView.ViewHolder(parent_view) {
        val txt_title: TextView = parent_view.findViewById<View>(R.id.txt_title) as TextView
        val img_cover: ImageView = parent_view.findViewById<View>(R.id.img_cover) as ImageView
    }

}