package com.leduytuanvu.vendingmachine.features.settings.home.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leduytuanvu.vendingmachine.core.common.AppThemeEnums
import com.leduytuanvu.vendingmachine.core.datasource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class HomeSettingsViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    @ApplicationContext private val context: Context
) : ViewModel() {

    // Function to set theme
    fun setTheme(theme: String) {
        try {
            viewModelScope.launch {
                sharedPreferencesDataSource.setTheme(theme)
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    // Function to get theme
    fun getTheme(): String {
        try {
            return sharedPreferencesDataSource.getTheme()
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
        return AppThemeEnums.LIGHT.value
    }
}
