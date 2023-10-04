package com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.viewmodel.HomeSettingsViewModel

@Composable
fun SetUpSlotSettingsScreen(navController: NavController) {
    val viewModel: HomeSettingsViewModel = hiltViewModel()

}