package mx.devlabs.zmovies.adapters

interface OnMovieListener {

    fun onMovieClick(position: Int)
    fun onCategoryClick(category: String)

}