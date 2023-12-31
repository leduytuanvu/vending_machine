package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.statusBarVendingMachine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.adsVendingMachine.AdsVendingMachineViewModel

@Composable
fun StatusBarVendingMachineComposable() {
    val viewModel: AdsVendingMachineViewModel = hiltViewModel()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            // 30% of the screen height
            .height(screenHeight * 0.02f)
            .fillMaxWidth()
            .background(Color.Gray)
    ) {

    }
}