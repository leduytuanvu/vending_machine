package com.leduytuanvu.vendingmachine

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Do your global initializations here, such as setting up dependency injection,
        // initializing analytics, etc.
    }
}