package com.example.starverse.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.starverse.*
import com.example.starverse.utilties.LogUtils.TAG
import com.example.starverse.NasaApiUrls.nasaUrlKey
import com.example.starverse.StarVerse.Companion.getAppContext
import com.example.starverse.databinding.FragmentApodFragmentBinding
import com.example.starverse.repository.api_requests.NasaApiRequests.requestApod
import com.example.starverse.repository.models.Apod

import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Make video display when image is not available
// TODO: Animate text/ui to notify content is loading
// TODO: Add refresh swipe up in case info is not parsed
// TODO: Have data persist throughout lifecycle
// TODO: Add string data
// TODO: Add share button
// TODO: Stop background battery usage
// TODO: Allow the ability to see APODs of different days
// TODO: Get rid of imageViewLoader after image/video is displayed



/**
This is the NASA API's astronomy picture/video of the day (APOD). This fragment uses Volley and JSON to
fetch data from the API and display results onscreen. Further Info on https://github.com/nasa/apod-api
 */
class ApodFragment : Fragment(R.layout.fragment_apod_fragment) {

    private lateinit var viewModel: ViewModelFragment

 //   private val youtubeVideoSuperPlayer: YouTubeVideoPlayer? = null

    // View Bindings
    private var _binding: FragmentApodFragmentBinding? = null
    private val binding get() = _binding!!

    // Used to run on platforms prior to Android 3.0 (Honeycomb - API 11)
    private val supportFragmentManager: FragmentManager? = null

    ////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prevents a new fragment from being created and transacted if the existing fragment is
        // restored. Wrapped and checked by 'if' statement so a duplicate fragment will not be
        // created.
        if(savedInstanceState == null) {
            supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.apod_fragment, ApodFragment())
                    ?.commit()
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Fragment View Information upon creation
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View {


        _binding = FragmentApodFragmentBinding.inflate(inflater, container, false)

       // youtubeVideoSuperPlayer?.initialize(YOUTUBE_API_KEY, this)


        Log.e("APODFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(ViewModelFragment::class.java)

        // Instantiate the RequestQueue.
        val context = context
        val queue = Volley.newRequestQueue(context)



        // Create JSON Object using JSONObjectRequest
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, nasaUrlKey, null,
                { response ->
                    try {

                        /* The response call may crash if getActivity() is null. This will return the
                         response results if the getActivity() is not null. This is needed, or app
                         may crash! */
                        if (activity != null) {

                            // Places NASA's image/video in the imageView via Glide
                            if (response.has("hdurl")) {
                              //  binding.videoContent.visibility = GONE
                                val loadImage = Glide.with(this)
                                        .load(response.getString("hdurl"))
                                        .into(binding.mediaContent)

                            } else if (response.has("url")) {
                                binding.mediaContent.visibility = GONE

                             //   youtubeVideoSuperPlayer?.youTubeVidPlayer = binding.videoContent
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
         // Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        queue.add(jsonObjectRequest)

        // Refresh feature implementation. Remember to configure XML Refresh Widget
        // Not fully implemented as of yet
        binding.swipeRefresh.setOnRefreshListener {
            binding.swipeRefresh.isRefreshing = false
            Log.e("Refreshing", "Refresh")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        requestApod.enqueue(object : Callback<Apod> {
            override fun onResponse(call: Call<Apod>, response: Response<Apod>) {
                Toast.makeText(getAppContext(), "Successful", LENGTH_LONG).show()
                Log.d(TAG, "onResponse: ${response.body()?.title}")
            }

            override fun onFailure(call: Call<Apod>, t: Throwable) {
                Toast.makeText(getAppContext(), "Failure", LENGTH_LONG).show()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
//        p1?.loadVideo("wKJ9KzGQq0w")
//    }
//
//    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
//        TODO("Not yet implemented")
//    }
}


