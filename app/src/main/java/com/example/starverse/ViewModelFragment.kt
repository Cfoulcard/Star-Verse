package com.example.starverse

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * The ViewModel is destroyed when the associated fragment is detached, or
 * when the activity is finished. Before the ViewModel is destroyed, the onCleared()
 * callback is called to clean up the resources.
 */
class ViewModelFragment : ViewModel() {

    init {
        Log.e("ViewModelFragment", "ViewModel Created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("GUH", "Clear the ViewModel doctor!")
    }
}

// ViewModels need to be associated with a UI controller (Like Fragments and Activities)
// To associate the two, you create a reference to the ViewModel inside the UI controller.