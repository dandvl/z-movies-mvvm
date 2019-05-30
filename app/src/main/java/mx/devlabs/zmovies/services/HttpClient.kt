package mx.devlabs.zmovies.services

import android.util.Log
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
    }
}

