package com.example.starverse.ui

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.preference.PreferenceFragmentCompat
import com.example.starverse.R
import com.example.starverse.databinding.FragmentSettingsFragmentBinding


class SettingsFragment : Fragment(R.layout.fragment_settings_fragment) {




    // View Bindings
    private var _binding: FragmentSettingsFragmentBinding? = null
    private val binding get() = _binding!!

    private val supportFragmentManager: FragmentManager? = null

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Prevents a new fragment from being created and transacted if the existing fragment is
        // restored. Wrapped and checked by 'if' statement so a duplicate fragment will not be
        // created.
        if(savedInstanceState == null) {
            supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.settings_fragment, SettingsFragment())
                    ?.commit()
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        _binding = FragmentSettingsFragmentBinding.inflate(inflater, container, false)

        binding.switchLightDark.setOnClickListener() {
            val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (isNightTheme) {
                Configuration.UI_MODE_NIGHT_YES ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                Configuration.UI_MODE_NIGHT_NO ->
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        return binding.root
    }

    fun lightSwitcher(v: View) {
        val isNightTheme = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (isNightTheme) {
            Configuration.UI_MODE_NIGHT_YES ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            Configuration.UI_MODE_NIGHT_NO ->
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}