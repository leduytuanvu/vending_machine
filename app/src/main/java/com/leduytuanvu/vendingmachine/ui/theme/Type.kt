package com.leduytuanvu.vendingmachine.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun vendingMachineResponsiveTypography(): Typography {
    return Typography(
        bodySmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = vendingMachineResponsiveSize(14f).sp,
            lineHeight = vendingMachineResponsiveSize(24f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0.4f).sp,
            color = Color.Black
        ),
        bodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = vendingMachineResponsiveSize(16f).sp,
            lineHeight = vendingMachineResponsiveSize(26f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0.6f).sp,
            color = Color.Black
        ),
        bodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = vendingMachineResponsiveSize(18f).sp,
            lineHeight = vendingMachineResponsiveSize(28f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0.8f).sp,
            color = Color.Black
        ),

        titleSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = vendingMachineResponsiveSize(20f).sp,
            lineHeight = vendingMachineResponsiveSize(28f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0f).sp,
            color = Color.White
        ),
        titleMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = vendingMachineResponsiveSize(24f).sp,
            lineHeight = vendingMachineResponsiveSize(30f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0.2f).sp,
            color = Color.White
        ),
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Bold,
            fontSize = vendingMachineResponsiveSize(28f).sp,
            lineHeight = vendingMachineResponsiveSize(32f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0.4f).sp,
            color = Color.White
        ),

        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = vendingMachineResponsiveSize(12f).sp,
            lineHeight = vendingMachineResponsiveSize(16f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0.4f).sp,
            color = Color.Black
        ),
        labelMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = vendingMachineResponsiveSize(14f).sp,
            lineHeight = vendingMachineResponsiveSize(18f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0.6f).sp,
            color = Color.Black
        ),
        labelLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = vendingMachineResponsiveSize(16f).sp,
            lineHeight = vendingMachineResponsiveSize(20f).sp,
            letterSpacing = vendingMachineResponsiveLetterSpacing(0.8f).sp,
            color = Color.Black
        )
    )
}

@Composable
fun vendingMachineResponsiveSize(size: Float): Float {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    return when {
        screenWidthDp < 320.dp -> size
        screenWidthDp < 480.dp -> size + 2
        else -> size + 4
    }
}

@Composable
fun vendingMachineResponsiveLetterSpacing(size: Float): Float {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    return when {
        screenWidthDp < 320.dp -> size
        screenWidthDp < 480.dp -> size + 0.2f
        else -> size + 0.4f
    }
}

@Composable
fun vendingMachineResponsiveDp(size: Float): Dp {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    return when {
        screenWidthDp < 320.dp -> (size + 2).dp
        screenWidthDp < 480.dp -> (size + 4).dp
        else -> (size + 6).dp
    }
}
