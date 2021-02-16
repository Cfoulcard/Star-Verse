package com.example.restapitesting.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.restapitesting.R
import com.example.restapitesting.Singleton
import com.example.restapitesting.databinding.FragmentApodFragmentBinding
import org.json.JSONException

class ApodFragment : Fragment(R.layout.fragment_apod_fragment) {

    val applicationContext: Context? = null
    private lateinit var binding: FragmentApodFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentApodFragmentBinding.inflate(layoutInflater)
        // Instantiate the RequestQueue.
        val context = context
        val queue = Volley.newRequestQueue(context)
        
        // Parse the URL
        val url = "https://api.nasa.gov/planetary/apod/?api_key=tpzRnSgZLfddTEoBGSS1DeLtODnMFFNrKrTbAvEL"

        // Create JSON Object using JSONObjectRequest
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    try {
                        // Get NASA's title listed in the JSON
                        binding.astropic.text = "%s".format(response["title"])
                        // Put NASA's explanation as the text as listed in the JSON
                        binding.text2.text = "%s".format(response["explanation"])
                        // Get copyright information
                        binding.text.text = "Captured by %s".format(response["copyright"])
                        // Places NASA's image in the imageView
                        Glide.with(this).load(response.getString("hdurl")).into(binding.image)
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

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_apod_fragment, container, false)
    }
    }