package com.leduytuanvu.vendingmachine.features.settings.home.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.settings.home.presentation.viewmodel.HomeSettingsViewModel
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.ads.AdsVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.information.InformationVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.listProduct.ListProductVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.statusBar.StatusBarVendingMachineComposable


@Composable
fun HomeSettingsScreen(navController: NavController) {
    val viewModel: HomeSettingsViewModel = hiltViewModel()
    Column(modifier = Modifier.background(Color.Transparent)) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Home Settings Screen")
            Text(text = "Home Settings Screen2")
            Text(text = "Home Settings Screen3")
            Text(text = "Home Settings Screen4")
        }
    }
}