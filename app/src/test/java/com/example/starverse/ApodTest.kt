package com.example.starverse

import androidx.core.text.isDigitsOnly
import com.example.starverse.repository.models.Apod
import org.junit.Test
import org.junit.Assert.*
import java.lang.NullPointerException

class ApodTest {

    @Test
    fun apodFormat() {

        val apodTest = Apod(
            title = "MLG",
            explanation = "This is a MLG explanation ",
            date = "2077-03-01",
            copyright = "None",
            media_type = "image",
            url = "https://apod.nasa.gov/apod/image/2204/SouthPoleShadows_LRO_960.jpg",
            hdurl = String(),
            thumbnail_url = String(),
            service_version = "v1",
            resource = String(),
        )

        assertNotNull(apodTest.title)
        assertEquals(apodTest.title, "MLG")
        assertNotEquals(apodTest.title, "")

        assertEquals(apodTest.date?.contains("-"), true)
        assertNotEquals(apodTest.date?.contains("."), true)
        assertNotEquals(apodTest.date?.contains("/"), true)

        assertEquals(apodTest.media_type?.equals("image") == true || (apodTest.media_type?.equals("video") == true), true)
    }
    @Test
    fun throwApodException() {

        val apodTest = Apod(
            title = null,
            explanation = "This is a MLG explanation ",
            date = "2077-03-01",
            copyright = "None",
            media_type = "image",
            url = "https://apod.nasa.gov/apod/image/2204/SouthPoleShadows_LRO_960.jpg",
            hdurl = String(),
            thumbnail_url = String(),
            service_version = "v1",
            resource = String(),
        )

        if (apodTest.title == null) {
            throw NullPointerException("Should not be null")
        }
    }
}