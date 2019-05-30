package mx.devlabs.zmovies.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import mx.devlabs.zmovies.R

import kotlinx.android.synthetic.main.activity_initial.*
import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.mvvm.WebServices
import mx.devlabs.zmovies.services.HttpResponse
import mx.devlabs.zmovies.services.Routes
import mx.devlabs.zmovies.services.MovieServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.widget.SwipeRefreshLayout



class InitialActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        setSupportActionBar(findViewById(R.id.toolbar_player))

        progress_bar.visibility = View.GONE

        val ab = supportActionBar
        ab!!.setHomeAsUpIndicator(R.drawable.default_image)
        ab.setDisplayHomeAsUpEnabled(true)

        viewManager = LinearLayoutManager(this@InitialActivity)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        recyclerView = findViewById<RecyclerView>(R.id.recycler_movies).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
        }

        loadMovies()


        val pullToRefresh = findViewById<SwipeRefreshLayout>(R.id.pullToRefresh)
        pullToRefresh.setOnRefreshListener {
            loadMovies()
            pullToRefresh.isRefreshing = false
        }
    }

    private fun loadMovies(selectedDays : Int = 1){

        progress_bar.visibility = View.VISIBLE
        recycler_movies.visibility = View.GONE

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -selectedDays)

        var startDate = calendar.time
        var endDate = Date()

        val format = SimpleDateFormat("yyyy/MM/dd")
        val strStartDate = format.format(startDate)
        val strEndDate = format.format(endDate)

        Log.i("zmovies", "endDate"+ strEndDate)
        Log.i("zmovies", "startDate" + strStartDate)

        val services = WebServices.instance().create(MovieServices::class.java)
        //val movies = services.movies(Routes.API_KEY)
        val movies = services.lastMoviesIn(Routes.API_KEY, strStartDate, strEndDate)

        movies.enqueue(object: Callback<HttpResponse<Movie>> {
            override fun onFailure(call: Call<HttpResponse<Movie>>, t: Throwable) {
                Log.e("zmovies", t.message)
                progress_bar.visibility = View.GONE
                recycler_movies.visibility = View.VISIBLE
            }
            override fun onResponse(call: Call<HttpResponse<Movie>>, response: Response<HttpResponse<Movie>>) {
                //Log.i("zmovies", "URL:" + response.raw().request().url()+"")
                recycler_movies.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE

                val response = response?.body() as HttpResponse
                val movies = response.results

                Log.i("zmovies", "movies loaded:${movies.count()}")

                val sfwMovies = movies.filter { !it.adult }

//                viewAdapter = MovieAdapter(sfwMovies)
                recyclerView.adapter = viewAdapter

                /*for(movie in movies){
                    Log.i("zmovies", movie.id)
                }*/
            }
        })
    }

}
