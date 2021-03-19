package com.example.starverse

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.contextaware.ContextAware
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpResponse
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.starverse.ui.ApodFragment
import org.json.JSONException
import org.json.JSONObject

/**
 * The ViewModel is destroyed when the associated fragment is detached, or
 * when the activity is finished. Before the ViewModel is destroyed, the onCleared()
 * callback is called to clean up the resources.
 */

/**
 * Application context aware ViewModel for parsing JSON data to the APOD Fragment
 */
class ViewModelFragment(application: Application) : AndroidViewModel(application) {

   // val context = application
    // val queue = Volley.newRequestQueue(context)

    // Logcat to confirm ViewModel has been created
    init {
        Log.e("ViewModelFragment", "ViewModel Created!")
    }

        // Parse the URL with the API key. Follow the link below to grab your API from NASA!
        val url = "https://api.nasa.gov/planetary/apod/?api_key=$NASA_API_KEY"

        // Create JSON Object using JSONObjectRequest
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    try {

                        /* The response call may crash if getActivity() is null. This will return the
                     response results if the getActivity() is not null. This is needed, or app
                     may crash! */
                        // if (activity != null) {

                        // Places NASA's image/video in the imageView via Glide
                        if (response.has("hdurl")) {
                            //  Glide.with(context).load(response.getString("hdurl"))                           if (context != null) {
                            //   }
                            //} else if (response.has("url")) {

                            val mc: MediaPlayer? = null
                            //     mc?.setOnPreparedListener()
                            //     mc?.setScreenOnWhilePlaying(true)

                            //      binding.mediaContent.visibility = GONE
                            //     val video = Uri.parse(response.getString("url"))

                            //    binding.videoContent.setVideoURI(video)
                        }

                        // Get title information from JSON if listed in API - Hide if not listed
                        if (response.has("title")) {
                            "%s".format(response["title"])
                        } else {
                            //  binding.titleCardview.visibility = View.GONE
                        }

                        // Get copyright information from JSON if listed in API - Hide if not listed
                        if (response.has("copyright")) {
                            "Captured by %s".format(response["copyright"])
                        } else {
                            //  binding.copyrightCardview.visibility = View.GONE
                        }

                        // Get description information from JSON if listed in API - Hide if not listed
                        if (response.has("explanation")) {
                            "%s".format(response["explanation"])
                        } else {
                            //    binding.descriptionCardview.visibility = View.GONE
                        }

                    } catch (e: JSONException) {
                    }
                },
                { error ->
                }
        )

        // Use the RequestQueue via the Singleton class which is needed to parse the JSON
        //  Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    override fun onCleared() {
        super.onCleared()
        Log.e("GUH", "Clear the ViewModel, doctor!")
    }
}

// ViewModels need to be associated with a UI controller (Like Fragments and Activities)
// To associate the two, you create a reference to the ViewModel inside the UI controller.