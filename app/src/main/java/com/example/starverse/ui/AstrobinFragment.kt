package com.example.starverse.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starverse.R

class AstrobinFragment : Fragment(R.layout.fragment_astrobin_fragment) {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_astrobin_fragment, container, false)
    }
}