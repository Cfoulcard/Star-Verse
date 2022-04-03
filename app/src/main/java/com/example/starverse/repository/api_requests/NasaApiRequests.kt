package com.example.starverse.repository.api_requests

import com.example.starverse.NASA_API_KEY
import com.example.starverse.repository.models.ApodService

object NasaApiRequests {

    private val request = NasaServiceBuilder.buildService(ApodService::class.java)
    val requestApod = request.getApod(NASA_API_KEY)
}