package mx.devlabs.zmovies.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

        setSupportActionBar(toolbar)
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
        search_view.setQuery("capitan", true)
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
        movies_rv.adapter = mRecyclerAdapter
        var itemDecorator = VerticalSpacingItemDecorator(30)
        movies_rv.addItemDecoration(itemDecorator)
        movies_rv.layoutManager = LinearLayoutManager(this@MainActivity)

        movies_rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                //if it can't scroll anymore
                if (!movies_rv.canScrollVertically(1)) {
                    // search for the next page
                    moviesListVM.searchNextPage()
                }
            }
        })

    }

    override fun onMovieClick(position: Int) {
        var intent = Intent(this@MainActivity, MovieActivity::class.java)
        intent.putExtra("movie", mRecyclerAdapter?.getSelectedMovie(position))
        startActivity(intent)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.movie_search_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.action_categories){
            //do something
        }

        return super.onOptionsItemSelected(item)
    }

    //back button
    //categories
    //cancel loading
}