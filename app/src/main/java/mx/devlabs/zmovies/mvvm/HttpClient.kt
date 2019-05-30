package mx.devlabs.zmovies.mvvm

import mx.devlabs.zmovies.services.MoviesRoutes
import mx.devlabs.zmovies.services.Routes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HttpClient {

    companion object {

        var retrofit: Retrofit? = null

        fun instance(): Retrofit {

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(Routes.HOST)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }

            return retrofit!!
        }

        val moviesRoutes= instance().create(MoviesRoutes::class.java)
    }
}

