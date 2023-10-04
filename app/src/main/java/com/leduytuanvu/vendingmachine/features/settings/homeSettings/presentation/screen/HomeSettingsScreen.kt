package com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.viewmodel.HomeSettingsViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalDensity
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSettingsScreen(navController: NavController) {
    val viewModel: HomeSettingsViewModel = hiltViewModel()

    val padding = vendingMachineResponsiveDp(10f)

    val buttonModifier = Modifier
        .padding(top = padding, start = padding, end = padding)
        .clip(RoundedCornerShape(6.dp))
        .background(color = Color.Red, shape = RoundedCornerShape(6.dp))
        .fillMaxWidth()

    val textModifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
    
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "SETTINGS VENDING MACHINE", style = MaterialTheme.typography.titleSmall)
                    }
                },
                modifier = Modifier.height(vendingMachineResponsiveDp(52f))
            )
        }
    ) {
        innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(modifier = modifier.background(Color.Transparent)) {
            VendingMachineButtonComposable(
                onClick = {
                    navController.navigate("homeVendingMachine") {
                        popUpTo("homeSettings") {
                            inclusive = true
                        }
                    }
                },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "VENDING MACHINE", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("setUpSystemSettings") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "SET UP SYSTEM", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("setUpProductSettings") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "SET UP PRODUCT", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "SET UP LAYOUT PRODUCT", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "SET UP SLOT", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "SET UP LAYOUT SLOT", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "SET UP PAYMENT", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "SHOW LOG", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "GENERAL", style = MaterialTheme.typography.bodyMedium)
                }
            }

            VendingMachineButtonComposable(
                onClick = { navController.navigate("testSettings") },
                modifier = buttonModifier
            ) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {  // CenterStart aligns content to the start (left) and vertically centered
                    Text(modifier = textModifier, text = "TEST", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}