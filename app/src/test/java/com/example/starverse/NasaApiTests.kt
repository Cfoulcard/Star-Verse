package com.example.starverse

import org.junit.Assert.*
import org.junit.Test

class NasaApiTests {

    private val nasaApiUrls = NasaApiUrls
    private val nasaApiUrlKey = nasaApiUrls.nasaUrlKey

    @Test
    fun nasaUrlNotNull() {
        assertNotNull("Not Null", NasaApiUrls.nasaUrlKey)
    }

    @Test
    fun nasaUrlKeyIsCorrect() {
        assertEquals(nasaApiUrlKey, "https://api.nasa.gov/planetary/apod/?api_key=$NASA_API_KEY")
    }

    @Test
    fun nasaUrlKeyHasBaseUrl() {
        assertTrue(nasaApiUrlKey.startsWith("https://api.nasa.gov/"))
    }

    @Test
    fun nasaUrlKeyNoWWW() {
        assertFalse(nasaApiUrlKey.contains("www."))
    }

}