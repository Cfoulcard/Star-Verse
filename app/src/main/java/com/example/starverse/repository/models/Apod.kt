package com.example.starverse.repository.models

import com.example.starverse.NasaApiUrls.nasaUrlKey
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class Apod(
    val title: String,
    val explanation: String,
    val date: String,
    val copyright: String,
    val mediaType: String,
)

interface ApodService {

    @GET("title")
    fun getTitle(@Query(nasaUrlKey) key: String) : Call<Apod>
}