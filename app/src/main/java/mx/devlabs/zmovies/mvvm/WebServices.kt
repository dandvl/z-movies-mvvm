package mx.devlabs.zmovies.mvvm

import mx.devlabs.zmovies.services.MovieServices
import mx.devlabs.zmovies.services.Routes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WebServices {

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

        val movies= instance().create(MovieServices::class.java)
    }
}

