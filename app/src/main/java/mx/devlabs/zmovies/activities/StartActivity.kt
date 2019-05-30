package mx.devlabs.zmovies.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_start.*
import mx.devlabs.zmovies.R
import mx.devlabs.zmovies.adapters.MovieAdapter
import mx.devlabs.zmovies.adapters.OnMovieListener
import mx.devlabs.zmovies.models.Movie
import mx.devlabs.zmovies.testing.Testing
import mx.devlabs.zmovies.util.VerticalSpacingItemDecorator
import mx.devlabs.zmovies.mvvm.MoviesListViewModel

class StartActivity : BaseActivity(), OnMovieListener{

    private var moviesListViewModel: MoviesListViewModel? = null
    private var mRecyclerAdapter : MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        moviesListViewModel = ViewModelProviders.of(this).get(MoviesListViewModel::class.java)

        initRecyclerView()
        initSearchView()
        subscribeObservers()
    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    mRecyclerAdapter?.displayLoading()
                    moviesListViewModel?.searchMoviesApi(query, 1)
                    search_view.clearFocus()
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun subscribeObservers() {
        moviesListViewModel!!.movies.observe(this, object : Observer<List<Movie>> {
            override fun onChanged(movies: List<Movie>?) {
                if (movies != null) {
                    Testing.printMovies("RMC", movies)
                    moviesListViewModel?.isPerfomingQuery = false
                    mRecyclerAdapter?.setMovies(movies)
                }
            }
        })
    }


    private fun initRecyclerView(){
        mRecyclerAdapter = MovieAdapter(this@StartActivity)
        movies_list.adapter = mRecyclerAdapter
        var itemDecorator = VerticalSpacingItemDecorator(30)
        movies_list.addItemDecoration(itemDecorator)
        movies_list.layoutManager = LinearLayoutManager(this@StartActivity)
    }

    override fun onMovieClick(position: Int) {
        Log.i("RMC", "movie click")
    }

    override fun onCategoryClick(category: String) {
        Log.i("RMC", "category click")
    }

    override fun onBackPressed() {
        if(moviesListViewModel!!.onBackPressed()){
            super.onBackPressed()
        }else{
            //categories
        }
    }

    //loading
    //back button
    //categories


}