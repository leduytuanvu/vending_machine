package com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.R
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.ads.AdsVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.information.InformationVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.listProduct.ListProductVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.statusBar.StatusBarVendingMachineComposable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.graphics.Color

@Composable
fun HomeVendingMachineScreen(navController: NavController) {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val maxHeight = constraints.maxHeight.dp

        Image(
            painter = painterResource(id = R.drawable.background_home),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(modifier = Modifier.clipToBounds()) {
            Column(
                modifier = Modifier
                    .background(Color.Transparent)
                    .height(maxHeight)
            ) {
                AdsVendingMachineComposable()
                StatusBarVendingMachineComposable()
                InformationVendingMachineComposable()
                ListProductVendingMachineComposable(Modifier.fillMaxHeight())
            }
        }
    }
}

