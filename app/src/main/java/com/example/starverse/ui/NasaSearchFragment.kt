package com.example.starverse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.starverse.*
import com.example.starverse.databinding.FragmentNasaSearchFragmentBinding
import org.json.JSONException

class NasaSearchFragment : Fragment(R.layout.fragment_nasa_search_fragment) {

    // View Bindings
    private var _binding: FragmentNasaSearchFragmentBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
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
                    ?.replace(R.id.nasa_search_fragment, NasaSearchFragment())
                    ?.commit()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNasaSearchFragmentBinding.inflate(inflater, container, false)
        binding.searchView.query
        binding.searchView.queryHint = "Explore the universe!"


        // Instantiate the RequestQueue.
        val context = context
        val queue = Volley.newRequestQueue(context)

        // Parse the URL with the API key. Follow the link below to grab your API from NASA!
        val url = "https://images-api.nasa.gov/search?q={stars}/?api_key=tpzRnSgZLfddTEoBGSS1DeLtODnMFFNrKrTbAvEL"

        // Create JSON Object using JSONObjectRequest
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    try {

                        /* The response call may crash if getActivity() is null. This will return the
                         response results if the getActivity() is not null. This is needed, or app
                         may crash! */
                        if (activity != null) {

                            if (response.has("version")) {
                                binding.textView.text = "%s".format(response["version"])
                            } else {
                            //    binding.textView.visibility = View.GONE
                           //     Toast.makeText(context, url, Toast.LENGTH_SHORT).show()

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}