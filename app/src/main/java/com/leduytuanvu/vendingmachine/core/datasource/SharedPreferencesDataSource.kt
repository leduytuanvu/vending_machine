package com.leduytuanvu.vendingmachine.core.datasource

import android.content.Context
import android.content.SharedPreferences
import com.leduytuanvu.vendingmachine.core.common.AppConstants
import com.leduytuanvu.vendingmachine.core.common.AppThemeEnums
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferencesDataSource @Inject constructor(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(AppConstants.PREF_vendingMachine_APP, Context.MODE_PRIVATE)

    fun setString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue).toString()
    }

    fun setInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun setBoolean(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun remove(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun clear() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    // Set theme shared preference
    fun setTheme(theme: String) {
        setString(AppConstants.PREF_THEME, theme)
    }

    // Get theme shared preference
    fun getTheme(): String {
        return getString(AppConstants.PREF_THEME, AppThemeEnums.LIGHT.value)
    }
}
