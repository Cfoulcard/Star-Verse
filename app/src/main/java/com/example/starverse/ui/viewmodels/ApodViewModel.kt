package com.example.starverse.ui.viewmodels

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.starverse.R
import com.example.starverse.StarVerse
import com.example.starverse.repository.api_requests.NasaApiRequests
import com.example.starverse.repository.models.Apod
import com.example.starverse.utilties.LogUtils.TAG
import com.example.starverse.utilties.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApodViewModel: ViewModel() {

    private val data = MutableLiveData<Resource<Apod>>()

    private fun getData() {
        data.postValue(Resource.loading(null))

    }

    private fun requestApodInformation() {

        NasaApiRequests.requestApod.enqueue(object : Callback<Apod> {
            override fun onResponse(call: Call<Apod>, response: Response<Apod>) {

                Log.d(TAG, "onResponse: ${response.raw()}", )
                Log.d(TAG, "onResponse: ${response.body()}", )

                if (response.body()?.title != null) {

                }

                if (response.body()?.copyright != null) {

                } else {

                }

                if (response.body()?.explanation != null) {

                }

                if (response.body()?.hdurl != null) {

                }

            }

            override fun onFailure(call: Call<Apod>, t: Throwable) {
                Toast.makeText(StarVerse.getAppContext(), "Could not load data", Toast.LENGTH_LONG).show()
            }

        })
    }


    override fun onCleared() {
        super.onCleared()
    }


}