package com.example.starverse.repository.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class Apod(
    val title: String?,
    val explanation: String?,
    val date: String?,
    val copyright: String?,
    val media_type: String?,
    val url: String?,
    val hdurl: String?,
    val thumbnail_url : String?,
    val service_version : String?,
    val resource : String?,
)

interface ApodService {

    @GET("/planetary/apod/")
    fun getApod(@Query("api_key") key : String) : Call<Apod>

}