package mx.devlabs.zmovies.old


class MovieApiClient private constructor() {

//    private var mMovies: MutableLiveData<List<Movie>> = MutableLiveData()
//
//    private var mRetrieveMoviesRunnable: RetrieveAllMoviesRunnable? = null
//    private var mRetrieveAllMoviesRunnable: RetrieveAllMoviesRunnable? = null
//
//    val movies: LiveData<List<Movie>> get() = mMovies
//
//    fun searchMovies(query: String, page: Int) {
//        if (mRetrieveMoviesRunnable != null) {
//            mRetrieveMoviesRunnable = null
//        }
//        mRetrieveMoviesRunnable = RetrieveAllMoviesRunnable(query, page)
//        val handler = AppExecutors.get().networkIO().submit(mRetrieveMoviesRunnable)
//
//        // Set a timeout for the data refresh
//        AppExecutors.get().networkIO().schedule(Runnable {
//            // let the user know it timed out
//            handler.cancel(true)
//        }, 5000L, TimeUnit.MILLISECONDS)
//    }
//
////    fun getAllMoviesApi() {
////        if (mRetrieveAllMoviesRunnable != null) {
////            mRetrieveAllMoviesRunnable = null
////        }
////        mRetrieveAllMoviesRunnable = RetrieveAllMoviesRunnable(query, page)
////        val handler = AppExecutors.get().networkIO().submit(mRetrieveAllMoviesRunnable)
////
////        AppExecutors.get().networkIO().schedule({
////            // let the user know it timed out
////            handler.cancel(true)
////        }, 5000L, TimeUnit.MILLISECONDS)
////    }
//
//
//    private inner class RetrieveAllMoviesRunnable constructor(private val query: String, private val page: Int) : Runnable {
//        private var cancelRequest: Boolean = false
//
//        override fun run() {
//
//            try {
//
//                val response = getMovies(query, page).execute()
//
//                if (cancelRequest) {
//                    return
//                }
//                if (response.code() == 200) {
//                    val response = response?.body() as HttpResponse
//                    val list = response.results
//
//                    mMovies.postValue(list)
//                } else {
//                    val error = response.errorBody()!!.string()
//                    Log.e(TAG, "run: error: $error")
//                    mMovies.postValue(null)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                mMovies.postValue(null)
//            }
//
//        }
//
//        private fun getMovies(query : String, page : Int): Call<HttpResponse<Movie>> {
////            if(page < 0){
////                page = 1
////            }
//            return HttpClient.moviesRoutes.searchMovies("en-US", query, page, false)
////            return ServiceGenerator.getMoviesApi.popularMovies(Constants.API_KEY)
//        }
//
//        fun cancelRequest() {
//            Log.d(TAG, "cancelRequest: canceling the retrieval query")
//            cancelRequest = true
//        }
//    }
//
//
//    private inner class RetrieveMoviesRunnable constructor(private val query: String, private val pageNumber: Int) : Runnable {
//        private var cancelRequest: Boolean = false
//
//        init {
//            cancelRequest = false
//        }
//
//        override fun run() {
//
//            try {
//                val response = getMovies().execute()
//                if (cancelRequest) {
//                    return
//                }
//                if (response.code() == 200) {
//
//                    val response = response?.body() as HttpResponse
//                    val list = response.results
//
////                    if (pageNumber == 1) {
//                        mMovies.postValue(list)
////                    } else {
////                        val currentMovies = mMovies
////                        currentMovies!!.addAll(list)
////                        mMovies.postValue(currentMovies)
////                    }
//                } else {
//                    val error = response.errorBody()!!.string()
//                    Log.e(TAG, "run: error: $error")
//                    mMovies.postValue(null)
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                mMovies.postValue(null)
//            }
//
//        }
//
//        private fun getMovies(): Call<HttpResponse<Movie>> {
//            return HttpClient.moviesRoutes.movies(Routes.API_KEY)
//        }
//
//        private fun cancelRequest() {
//            Log.d(TAG, "cancelRequest: canceling the retrieval query")
//            cancelRequest = true
//        }
//    }
//
//    companion object {
//
//        private val TAG = "MovieApiClient"
//
//        private var instance: MovieApiClient? = null
//
//        fun getInstance(): MovieApiClient {
//            return (instance ?: MovieApiClient())
//        }
//    }
//
//    fun cancelRequest(){
//        if(mRetrieveAllMoviesRunnable != null){
//            mRetrieveAllMoviesRunnable.let { it?.cancelRequest() }
//        }
//    }
}











