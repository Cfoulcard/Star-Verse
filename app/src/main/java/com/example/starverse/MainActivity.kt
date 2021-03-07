package com.example.starverse

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.preference.PreferenceManager
import com.example.starverse.databinding.ActivityMainBinding
import com.example.starverse.ui.SettingsFragment
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem


/** This Activity is mainly used to setup the Nav Graph and Bottom Navigation
 * This will be inflated upon all fragments.
 */
class MainActivity : AppCompatActivity() {

    // Initiate the ViewModel to have configuration data persist
    private lateinit var viewModel: ViewModelFragment

    // View Binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  Log.e("MainActivity", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(ViewModelFragment::class.java)

        // Ensures settings are properly initialized with their default values
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false)
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
      //  val switchPref = sharedPref.getBoolean("sync", false)

       // Toast.makeText(this, switchPref.toString(), Toast.LENGTH_SHORT).show()

        supportActionBar?.hide()

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





