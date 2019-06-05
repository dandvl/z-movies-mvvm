package mx.devlabs.zmovies.services


object Config {
    const val HOST = "https://api.themoviedb.org/3/"
    const val IMG_HOST = "https://image.tmdb.org/t/p/original/"
    const val API_KEY = "45b8e2f82c6fb1b24e2b19a8087b855e"

    //Routes
    const val LAST_CHANGES = "movie/changes"
    const val POPULAR_MOVIES = "/3/discover/movie?sort_by=popularity.desc"
    const val MOVIE_DETAIL = "/3/movie/{movie_id}?api_key=$API_KEY"
    const val SEARCH_MOVIES = "/3/search/movie?api_key=$API_KEY"
}