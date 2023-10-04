package com.leduytuanvu.vendingmachine.ui.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color.Transparent,
    surface = Pink80,
    onPrimary = Pink80,
    onSecondary = Pink80,
    onTertiary = Pink80,
    onBackground = Pink80,
    onSurface = Pink80,
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color.Transparent,
    surface = PurpleGrey40,
    onPrimary = PurpleGrey40,
    onSecondary = PurpleGrey40,
    onTertiary = PurpleGrey40,
    onBackground = PurpleGrey40,
    onSurface = PurpleGrey40,
)

@Composable
fun VendingMachineTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    // Get the Local Context
    val context = LocalContext.current
    val activity = context.findActivity()
    val window = activity?.window
    val decorView = window?.decorView

    SideEffect {
        // Adjust the color of the status bar to match your theme.
        window?.statusBarColor = colorScheme.primary.toArgb()

        // This is where you set the app to fullscreen.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window?.setDecorFitsSystemWindows(false)
            decorView?.windowInsetsController?.hide(WindowInsets.Type.systemBars())
        } else {
            @Suppress("DEPRECATION")
            decorView?.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN
            )
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = vendingMachineResponsiveTypography(),
        content = content
    )
}

// Extension function to find the Activity from Context.
fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}