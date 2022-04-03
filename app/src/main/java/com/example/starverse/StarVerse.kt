package com.example.starverse

import android.app.Application
import android.content.Context

class StarVerse : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {

        private var context: Context? = null

        fun getAppContext(): Context? {
            return context
        }
    }
}