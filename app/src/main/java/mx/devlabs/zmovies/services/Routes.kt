package mx.devlabs.zmovies.services


object Routes {
    const val HOST = "https://api.themoviedb.org/3/"
    const val IMG_HOST = "https://image.tmdb.org/t/p/original/"
    const val API_KEY = "45b8e2f82c6fb1b24e2b19a8087b855e"
    const val LAST_CHANGES = "movie/changes"
    const val MOVIE_DETAIL = "movie/{movie_id}?api_key=$API_KEY"
    const val POPULAR_MOVIES = "/3/discover/movie?sort_by=popularity.desc"
    const val SEARCH_MOVIES = "/3/search/movie?api_key=$API_KEY"
//    const val SEARCH_MOVIES = "/3/search/movie?api_key=$API_KEY&language={lang}&query={query}&page={page}&include_adult={include_adult}"
//    const val SEARCH_MOVIES = "/3/search/movie?api_key=$API_KEY&language=en-US&query=capitan&page=1&include_adult=false"
}