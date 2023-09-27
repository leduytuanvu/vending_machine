package com.leduytuanvu.vendingmachine.core.common

object AppConstants {
    // Api
    const val BASE_URL = "https://api.example.com/"

    // Shared preferences keys
    const val PREF_vendingMachine_APP = "PREF_vendingMachine_APP"
    const val PREF_LANGUAGE = "PREF_LANGUAGE"
    const val PREF_THEME = "PREF_THEME"

    // Other constants
    const val DEFAULT_LANGUAGE = "en"
    const val DEFAULT_THEME = "Light"

    // Feature flags
    const val ENABLE_PAYMENT_FEATURE = true
    const val ENABLE_SETTINGS_FEATURE = true

    // Timeouts
    const val NETWORK_TIMEOUT = 60L

    // Miscellaneous
    const val APP_DATABASE_NAME = "vendingMachine.db"
}
