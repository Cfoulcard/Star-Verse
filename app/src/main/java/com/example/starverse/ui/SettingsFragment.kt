package com.example.starverse.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import androidx.preference.SwitchPreferenceCompat
import com.example.starverse.R

/**
 * The Fragment linked to the settings tab on the bottom nav. All settings are taken care of here.
 */
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        loadSettings()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val currentFragment: Fragment? = requireFragmentManager().findFragmentByTag("YourFragmentTag")
        val fragmentTransaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        fragmentTransaction.detach(this)
        fragmentTransaction.attach(this)
        fragmentTransaction.commit()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private fun loadSettings() {
        val sp: SharedPreferences? = PreferenceManager.getDefaultSharedPreferences(context)
        val toggleTheme = sp?.getBoolean("sync", false)

        // Means Night mode is not active and switch is not "on"
        if (toggleTheme == false) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            MyPreferences(requireContext()).darkMode = 0
        }

        // Means Night mode is active and switch is "on"
        if (toggleTheme == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            MyPreferences(requireContext()).darkMode = 1
        }
    }
}

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_settings)
        supportFragmentManager.beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
    }
}


class MyPreferences(context: Context) {

    companion object {
        private const val DARK_STATUS = "0"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var darkMode = preferences.getInt(DARK_STATUS, 0)
        set(value) = preferences.edit().putInt(DARK_STATUS, value).apply()

}




