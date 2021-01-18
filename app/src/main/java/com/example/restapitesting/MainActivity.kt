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
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.URI
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        val textView1 = findViewById<TextView>(R.id.astropic)
        val textView2 = findViewById<TextView>(R.id.text)
        val imageView = findViewById<ImageView>(R.id.image)

        // Parse the URL
        val url = "https://api.nasa.gov/planetary/apod/?api_key=tpzRnSgZLfddTEoBGSS1DeLtODnMFFNrKrTbAvEL"

        // Create JSON Object using JSONObjectRequest
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    // Get NASA's title listed in the JSON
                    textView1.text = "%s".format(response["title"])
                    // Put NASA's explanation as the text as listed in the JSON
                    textView2.text = "%s".format(response["explanation"])

                    // Places NASA's image in the imageView
                    Glide.with(this).load(response.getString("hdurl")).into(imageView)
                } catch (e: JSONException) {

                }
            },
            { error ->
                Toast.makeText(this, "Cannot get info", Toast.LENGTH_SHORT).show()
            }
        )

        // Use the RequestQueue which is needed to parse the JSON
        queue.add(jsonObjectRequest)

    }

    }






