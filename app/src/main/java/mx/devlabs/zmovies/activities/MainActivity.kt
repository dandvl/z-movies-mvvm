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
import mx.devlabs.zmovies.mvvm.MoviesListVM

class MainActivity : BaseActivity(), OnMovieListener{

    private lateinit var moviesListVM: MoviesListVM
    private var mRecyclerAdapter : MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        moviesListVM = ViewModelProviders.of(this).get(MoviesListVM::class.java)

        initRecyclerView()
        initSearchView()
        subscribeObservers()
    }

    private fun initSearchView() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener, android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotEmpty()) {
                    mRecyclerAdapter?.displayLoading()
                    moviesListVM?.searchMovies(query, 1)
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
        //ViewModel.LiveData.should be called addObserver then the parameters (LifeCycleOwner and the new  Observer)
        moviesListVM.movies.observe(this, Observer<List<Movie>> { movies ->
            if (movies != null && movies.isNotEmpty()) {
                Testing.printMovies("RMC", movies)
                moviesListVM.isPerformingQuery = false
                mRecyclerAdapter?.setMovies(movies)
            }else{
                Log.i("RMC", "No moviesRoutes")
            }
        })
    }


    private fun initRecyclerView(){
        mRecyclerAdapter = MovieAdapter(this@MainActivity)
        movies_list.adapter = mRecyclerAdapter
        var itemDecorator = VerticalSpacingItemDecorator(30)
        movies_list.addItemDecoration(itemDecorator)
        movies_list.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    override fun onMovieClick(position: Int) {
        Log.i("RMC", "movie click")
    }

    override fun onCategoryClick(category: String) {
        Log.i("RMC", "category click")
    }

    override fun onBackPressed() {
        if(moviesListVM!!.onBackPressed()){
            super.onBackPressed()
        }else{
            //categories
        }
    }

    //loading
    //back button
    //categories

}