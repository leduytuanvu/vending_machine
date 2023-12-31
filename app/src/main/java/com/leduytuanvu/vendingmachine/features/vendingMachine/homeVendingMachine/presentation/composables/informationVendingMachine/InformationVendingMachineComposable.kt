package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.informationVendingMachine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.adsVendingMachine.AdsVendingMachineViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable

@Composable
fun InformationVendingMachineComposable(navController: NavController) {

    val viewModel: AdsVendingMachineViewModel = hiltViewModel()
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Box(
        modifier = Modifier
            .height(screenHeight * 0.06f)
            .fillMaxWidth()
            .background(Color.Red)
    ) {
        VendingMachineButtonComposable(
            onClick = {
                navController.navigate("homeSettings") {
                    popUpTo("homeVendingMachine") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.Center)
                .clip(RoundedCornerShape(bottomEnd = 6.dp, topEnd = 6.dp))
                .background(color = Color.Green, shape = RoundedCornerShape(6.dp))
        ) {
            Text(text = "Settings", color = Color.White)
        }
    }
}