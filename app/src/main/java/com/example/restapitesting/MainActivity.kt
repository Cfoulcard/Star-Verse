package com.example.restapitesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.restapitesting.databinding.ActivityMainBinding
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.astrobin_fragment,
                R.id.apod_fragment,
                R.id.settings_fragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.ic_notification,
                R.drawable.avd_notification,
                R.id.astrobin_fragment
            ),
            CbnMenuItem(
                R.drawable.ic_dashboard,
                R.drawable.avd_dashboard,
                R.id.apod_fragment
            ),
            CbnMenuItem(
                R.drawable.ic_home,
                R.drawable.avd_home,
                R.id.settings_fragment
            )
        )

        binding.navView.setMenuItems(menuItems, 1)
        binding.navView.setupWithNavController(navController)
    }
}


        // Instantiate the RequestQueue.
        /*val queue = Volley.newRequestQueue(this)

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

    }*/







