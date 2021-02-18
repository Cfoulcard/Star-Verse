package com.example.restapitesting

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.restapitesting.ui.ApodFragment

/**
 * Because this app can make constant use of the network, a Singleton class
 * is used to make use of the RequestQueue. Singleton will be a single instance
 * shared across the entire app. Only one instance of the Singleton is allowed.
 *
 * The app as a result will work better and faster because less objects need to
 * be created.
 */
class Singleton(context: ApodFragment) {

    companion object {

        @Volatile
        private var INSTANCE: Singleton? = null
        fun getInstance(context: ApodFragment) =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: Singleton(context).also {
                        INSTANCE = it
                    }
                }
    }

    private val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}