package com.leduytuanvu.vendingmachine

import android.app.Application
import android.util.Log
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.network.RetrofitConfig
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Do your global initializations here, such as setting up dependency injection,
        // initializing analytics, etc.
    }
}