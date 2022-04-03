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
import com.bumptech.glide.Glide
import com.example.starverse.R
import com.example.starverse.StarVerse.Companion.getAppContext
import com.example.starverse.ViewModelFragment
import com.example.starverse.databinding.FragmentApodFragmentBinding
import com.example.starverse.repository.api_requests.NasaApiRequests.requestApod
import com.example.starverse.repository.models.Apod
import com.example.starverse.utilties.LogUtils.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

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
This is the NASA API's astronomy picture/video of the day (APOD). This fragment uses Retrofit to
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

        viewModel = ViewModelProvider(this).get(ViewModelFragment::class.java)




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

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

        requestApodInformation()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun requestApodInformation() {
        try {
            if (requestApod.isExecuted) {
                requestApod.clone().enqueue(object: Callback<Apod> {
                    override fun onResponse(call: Call<Apod>, response: Response<Apod>) {

                        Log.d(TAG, "onResponse: ${response.raw()}",)
                        Log.d(TAG, "onResponse: ${response.body()}",)

                        if (response.body()?.title != null) {
                            binding.titleText.text =
                                response.body()?.title ?: getString(R.string.no_title)
                        }

                        if (response.body()?.copyright != null) {
                            binding.copyrightText.text =
                                response.body()?.copyright ?: getString(R.string.no_copyright)
                        } else {
                            binding.copyrightText.visibility = GONE
                            binding.copyrightCardview.visibility = GONE
                        }

                        if (response.body()?.explanation != null) {
                            binding.descriptionText.text =
                                response.body()?.explanation ?: getString(R.string.no_explanation)
                        }

                        if (response.body()?.hdurl != null) {
                            val loadImage = Glide.with(this@ApodFragment)
                                .load(response.body()!!.hdurl)
                                .into(binding.mediaContent)

                            loadImage.waitForLayout()
                        }

                    }

                    override fun onFailure(call: Call<Apod>, t: Throwable) {
                        Toast.makeText(getAppContext(), "Could not load data", LENGTH_LONG).show()
                    }

                })
            }
            requestApod.enqueue(object : Callback<Apod> {
                override fun onResponse(call: Call<Apod>, response: Response<Apod>) {



                    Log.d(TAG, "onResponse: ${response.raw()}",)
                    Log.d(TAG, "onResponse: ${response.body()}",)

                    if (response.body()?.title != null) {
                        binding.titleText.text =
                            response.body()?.title ?: getString(R.string.no_title)
                    }

                    if (response.body()?.copyright != null) {
                        binding.copyrightText.text =
                            response.body()?.copyright ?: getString(R.string.no_copyright)
                    } else {
                        binding.copyrightText.visibility = GONE
                        binding.copyrightCardview.visibility = GONE
                    }

                    if (response.body()?.explanation != null) {
                        binding.descriptionText.text =
                            response.body()?.explanation ?: getString(R.string.no_explanation)
                    }

                    if (response.body()?.hdurl != null) {
                        val loadImage = Glide.with(this@ApodFragment)
                            .load(response.body()!!.hdurl)
                            .into(binding.mediaContent)

                        loadImage.waitForLayout()
                    }

                }

                override fun onFailure(call: Call<Apod>, t: Throwable) {
                    Toast.makeText(getAppContext(), "Could not load data", LENGTH_LONG).show()
                }

            })
        } catch (e: IllegalStateException) {
            Log.e(TAG, "requestApodInformation: already executed request")
        }
    }

//    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
//        p1?.loadVideo("wKJ9KzGQq0w")
//    }
//
//    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
//        TODO("Not yet implemented")
//    }
}


