package com.example.restapitesting.ui

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.restapitesting.API_KEY
import com.example.restapitesting.BuildConfig
import com.example.restapitesting.R
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

/**
This is the NASA API's picture/video of the day. This fragment uses Volley and JSON to
fetch data from the API and display results onscreen.
 */
class ApodFragment : Fragment(R.layout.fragment_apod_fragment) {


    val applicationContext: Context? = null
    // View Binding
    private var _binding: FragmentApodFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApodFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instantiate the RequestQueue.
        val context = context
        val queue = Volley.newRequestQueue(context)

        // Parse the URL with API key
        val url = "https://api.nasa.gov/planetary/apod/?api_key=$API_KEY"



        // Create JSON Object using JSONObjectRequest
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    try {

                        /* The response call may crash if getActivity() is null. This will return the
                         response results if the getActivity() is not null. This is needed, or app
                         may crash! */
                        if (activity != null) {

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
                        }
                    } catch (e: JSONException) {
                    }
                },
                { error ->
                    //   Toast.makeText(this, "Cannot get info", Toast.LENGTH_SHORT).show()
                }
        )

        // Use the RequestQueue via the Singleton class which is needed to parse the JSON
        // Singleton.getInstance(context = ApodFragment()).addToRequestQueue(jsonObjectRequest)
        queue.add(jsonObjectRequest)
    }


}
