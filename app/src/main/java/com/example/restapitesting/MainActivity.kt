package com.example.restapitesting

import android.app.DownloadManager
import android.graphics.BitmapFactory
import android.net.sip.SipSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun clicker(v: View) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val textView = findViewById<TextView>(R.id.text)
        val imageView = findViewById<ImageView>(R.id.image)

        // Parse the URL
        val url = "https://api.nasa.gov/planetary/apod/?api_key=tpzRnSgZLfddTEoBGSS1DeLtODnMFFNrKrTbAvEL"

        // Create JSON Object
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    textView.text = "Response: %s".format(response["explanation"])
                    val bmpUrl = URL (response.getString("hdurl"))

                },
                { error ->
                    // TODO: Handle error
                }
        )

        // Use the RequestQueue which is needed to parse the JSON
        queue.add(jsonObjectRequest)
    }
}



