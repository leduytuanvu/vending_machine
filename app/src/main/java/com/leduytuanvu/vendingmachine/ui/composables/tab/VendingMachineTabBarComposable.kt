package com.leduytuanvu.vendingmachine.ui.composables.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp

@Composable
fun VendingMachineTabBarComposable(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(if (isSelected) Color.Green else Color.Gray)
            .clickable(onClick = onClick)
            .height(vendingMachineResponsiveDp(40f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}