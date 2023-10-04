package com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class HomeSettingsViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    @ApplicationContext private val context: Context
) : ViewModel() {

    fun getTheme() : String {
        return sharedPreferencesDataSource.getTheme()
    }

    fun setTheme(theme: String) {
        sharedPreferencesDataSource.setTheme(theme)
    }
}
