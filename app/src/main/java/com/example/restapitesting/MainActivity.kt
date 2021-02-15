package com.example.restapitesting

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem
import np.com.susanthapa.curved_bottom_navigation.CurvedBottomNavigationView
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_notification, // the icon
                R.drawable.avd_notification, // the AVD that will be shown in FAB
            ),
            CbnMenuItem(
                R.drawable.ic_dashboard,
                R.drawable.avd_dashboard,
            ),
            CbnMenuItem(
                R.drawable.ic_settings,
                R.drawable.avd_settings,
            )
        )
        val navView = findViewById<CurvedBottomNavigationView>(R.id.nav_view)
        navView.setMenuItems(menuItems, 1)
        navView.isShown


        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        val textView1 = findViewById<TextView>(R.id.astropic)
        val textView2 = findViewById<TextView>(R.id.text)
        val textView3 = findViewById<TextView>(R.id.text2)
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
                    // Get copyright information
                    textView3.text = "Captured by %s".format(response["copyright"])
                    // Places NASA's image in the imageView
                    Glide.with(this).load(response.getString("hdurl")).into(imageView)
                } catch (e: JSONException) {
                }
            },
            { error ->
                Toast.makeText(this, "Cannot get info", Toast.LENGTH_SHORT).show()
            }
        )

        // Use the RequestQueue via the Singleton class which is needed to parse the JSON
        Singleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

}






