package com.example.pushapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val service = NotificationService(applicationContext)
        service.createNotificationChannel()
    }
}