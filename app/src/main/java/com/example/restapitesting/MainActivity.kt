package com.example.restapitesting

import android.app.DownloadManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)

        // RESTapi section

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        // Parse the URL
        val url = "https://api.nasa.gov/planetary/apod/?api_key=tpzRnSgZLfddTEoBGSS1DeLtODnMFFNrKrTbAvEL"

        //Create JSON Object
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    textView.text = "Response: %s ${(response.toString())}"
                    Toast.makeText( this,"Hiiii", Toast.LENGTH_SHORT).show()
                },
                { error ->
                    Toast.makeText( this,"Bye", Toast.LENGTH_SHORT).show()
                }
        )

        // Use the RequestQueue which is needed to parse the JSON
        queue.add(jsonObjectRequest)
    }
}

