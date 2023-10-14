package com.leduytuanvu.vendingmachine.ui.composables.loading

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VendingMachineCircularProgessIndicator() {
    CircularProgressIndicator(
        color = Color.Black,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}