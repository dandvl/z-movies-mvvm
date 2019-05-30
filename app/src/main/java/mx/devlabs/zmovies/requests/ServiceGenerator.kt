package mx.devlabs.zmovies.requests

import mx.devlabs.zmovies.services.Routes
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ServiceGenerator {

    companion object {

        private val retrofitBuilder = Retrofit.Builder()
                .baseUrl(Routes.HOST)
                .addConverterFactory(GsonConverterFactory.create())

        private val retrofit = retrofitBuilder.build()

        val getMoviesApi= retrofit.create(MovieApi::class.java)
    }
}















