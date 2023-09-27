package com.leduytuanvu.vendingmachine.core.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import java.util.Locale

object LanguageUtils {
    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    fun saveLanguage(context: Context, language: String) {
        val prefsEditor = getSharedPreferences(context).edit()
        prefsEditor.putString("PREF_USER_LANGUAGE", language)
        prefsEditor.apply()
        updateLanguage(context, language)
    }

    fun loadLanguage(context: Context) {
        val prefs = getSharedPreferences(context)
        val language = prefs.getString("PREF_USER_LANGUAGE", "en")
        updateLanguage(context, language)
    }

    private fun updateLanguage(context: Context, language: String?) {
        val locale = Locale(language.toString())
        Locale.setDefault(locale)

        val res: Resources = context.resources
        val config: Configuration = res.configuration
        config.setLocale(locale)

        context.createConfigurationContext(config)
    }
}
