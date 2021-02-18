package com.example.restapitesting.ui

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.restapitesting.API_KEY
import com.example.restapitesting.R
import com.example.restapitesting.Singleton
import com.example.restapitesting.databinding.FragmentApodFragmentBinding
import org.json.JSONException

// TODO: Make video display when image is not available
// TODO: Animate text/ui to notify content is loading
// TODO: Fix error on older devices not inflating fragment
//    java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.restapitesting/com.example.restapitesting.MainActivity}: android.view.InflateException: Binary XML file line #17: Binary XML file line #17: Error inflating class androidx.appcompat.widget.ActionBarOverlayLayout
// TODO: Add refresh swipe up in case info is not parsed
// TODO: Have data persist throughout lifecycle
// TODO: COMPLETED - Take CardViews away if information is not available
// TODO: Add string data
// TODO: Add share button
// TODO: Stop background battery usage

/**
This is the NASA API's astronomy picture/video of the day (APOD). This fragment uses Volley and JSON to
fetch data from the API and display results onscreen.
 */
class ApodFragment : Fragment(R.layout.fragment_apod_fragment) {

    // App context that connects with the Singleton class. Grabs data from the RequestQueue
    val applicationContext: Context? = null

    // View Bindings
    private var _binding: FragmentApodFragmentBinding? = null
    private val binding get() = _binding!!

    // Fragment View Information upon creation
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentApodFragmentBinding.inflate(inflater, container, false)

        // Refresh feature implementation. Remember to configure XML Refresh Widget
        // Not fully implemented as of yet
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
        }

        return binding.root
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Instantiate the RequestQueue.
        val context = context
        val queue = Volley.newRequestQueue(context)

        // Parse the URL with the API key. Follow the link below to grab your API from NASA!
        val url = "https://api.nasa.gov/planetary/apod/?api_key=$API_KEY"

        // Create JSON Object using JSONObjectRequest
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    try {

                        /* The response call may crash if getActivity() is null. This will return the
                         response results if the getActivity() is not null. This is needed, or app
                         may crash! */
                        if (activity != null) {

                            // Places NASA's image/video in the imageView via Glide
                            if (response.has("hdurl")) {
                                binding.videoContent.visibility = GONE
                                Glide.with(this).load(response.getString("hdurl")).into(binding.mediaContent)
                            } else if (response.has("url")) {

                                val mc: MediaPlayer? = null
                                //     mc?.setOnPreparedListener()
                                //     mc?.setScreenOnWhilePlaying(true)

                                binding.mediaContent.visibility = GONE
                                val video = Uri.parse(response.getString("url"))

                                binding.videoContent.setVideoURI(video)
                            }

                            // Get title information from JSON if listed in API - Hide if not listed
                            if (response.has("title")) {
                                binding.titleText.text = "%s".format(response["title"])
                            } else {
                                binding.titleCardview.visibility = GONE
                            }

                            // Get copyright information from JSON if listed in API - Hide if not listed
                            if (response.has("copyright")) {
                                binding.copyrightText.text = "Captured by %s".format(response["copyright"])
                            } else {
                                binding.copyrightCardview.visibility = GONE
                            }

                            // Get description information from JSON if listed in API - Hide if not listed
                            if (response.has("explanation")) {
                                binding.descriptionText.text = "%s".format(response["explanation"])
                            } else {
                                binding.descriptionCardview.visibility = GONE
                            }
                        }
                    } catch (e: JSONException) {
                    }
                },
                { error ->
                       Toast.makeText(context, "Cannot get info", Toast.LENGTH_SHORT).show()
                }
        )

        // Use the RequestQueue via the Singleton class which is needed to parse the JSON
      //  Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        queue.add(jsonObjectRequest)
    }
}


