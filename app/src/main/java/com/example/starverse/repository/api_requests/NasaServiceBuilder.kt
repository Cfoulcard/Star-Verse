package com.example.starverse.repository.api_requests

import com.example.starverse.R
import com.example.starverse.StarVerse.Companion.getAppContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** Builds our Retrofit OkHttpClient to utilize request */
object NasaServiceBuilder {

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(getAppContext()?.getString(R.string.nasa_url).toString())
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

}