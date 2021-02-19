package com.example.starverse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.starverse.databinding.ActivityMainBinding
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

/** This Activity is mainly used to setup the Nav Graph and Bottom Navigation
 * This will be inflated upon all fragments.
 */
class MainActivity : AppCompatActivity() {

    // View Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Finds the Navigation Graph
        val navController = findNavController(R.id.nav_host_fragment)

        // For Bottom Navigation Menu. Uses Curved Bottom Navigation Library
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
                    R.drawable.ic_dashboard,
                    R.drawable.avd_dashboard,
                    R.id.astrobin_fragment
            ),
            CbnMenuItem(
                    R.drawable.ic_home,
                    R.drawable.avd_home,
                    R.id.apod_fragment
            ),
            CbnMenuItem(
                    R.drawable.ic_settings,
                    R.drawable.avd_settings,
                    R.id.settings_fragment
            )
        )
        binding.navView.setMenuItems(menuItems, 1)
        binding.navView.setupWithNavController(navController)


    }
}




