package com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.viewmodel.HomeSettingsViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable

@Composable
fun SetUpProductSettingsScreen(navController: NavController) {
    val viewModel: HomeSettingsViewModel = hiltViewModel()

}