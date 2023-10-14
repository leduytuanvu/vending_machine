package com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeSettingsScreen(navController: NavController) {
    // Get the screen configuration
    val configuration = LocalConfiguration.current
    // Retrieve screen height in pixels
    val screenHeight = configuration.screenHeightDp
    // App bar height
    val appBarHeight = screenHeight * 0.038f

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "SETTINGS", style = MaterialTheme.typography.titleMedium)
                    }
                },
                modifier = Modifier.height(vendingMachineResponsiveDp(appBarHeight))
            )
        }
    ) {
        innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(modifier = modifier.padding(vendingMachineResponsiveDp(30f))) {
            VendingMachineButtonComposable(
                onClick = {
                    navController.navigate("homeVendingMachine") {
                        popUpTo("homeSettings") {
                            inclusive = true
                        }
                    }
                },
                isCenter = false,
                text = "VENDING MACHINE",
                paddingHorizontal = 26,
                paddingVertical = 20
            )
            
            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("setUpSystemSettings") },
                isCenter = false,
                text = "SET UP SYSTEM",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("setUpProductSettings") },
                isCenter = false,
                text = "SET UP PRODUCT",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                isCenter = false,
                text = "SET UP LAYOUT PRODUCT",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("setUpSlotSettings") },
                isCenter = false,
                text = "SET UP SLOT",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                isCenter = false,
                text = "SET UP LAYOUT SLOT",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                isCenter = false,
                text = "SET UP PAYMENT",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                isCenter = false,
                text = "SHOW LOG",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("homeVendingMachine") },
                isCenter = false,
                text = "GENERAL",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))

            VendingMachineButtonComposable(
                onClick = { navController.navigate("testSettings") },
                isCenter = false,
                text = "TEST",
                paddingHorizontal = 26,
                paddingVertical = 20
            )

            Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(18f)))
        }
    }
}