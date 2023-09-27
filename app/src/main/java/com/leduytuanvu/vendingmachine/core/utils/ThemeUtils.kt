package com.leduytuanvu.vendingmachine.core.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.leduytuanvu.vendingmachine.core.common.AppConstants

object ThemeUtils {
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    fun saveTheme(context: Context, theme: String) {
        val prefsEditor = getSharedPreferences(context).edit()
        prefsEditor.putString(AppConstants.PREF_THEME, theme)
        prefsEditor.apply()
    }

    fun loadTheme(context: Context) {
        val prefs = getSharedPreferences(context)
        val theme = prefs.getString(AppConstants.PREF_THEME, AppConstants.DEFAULT_THEME)
        setTheme(theme)
    }

    private fun setTheme(theme: String?) {
        when (theme) {
            "Light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "Dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
