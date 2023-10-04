package com.leduytuanvu.vendingmachine.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leduytuanvu.vendingmachine.features.authentication.login.presentation.screens.LoginScreen
import com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.screen.HomeSettingsScreen
import com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.presentation.screen.SetUpProductSettingsScreen
import com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.screen.SetUpSlotSettingsScreen
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.screen.SetUpSystemSettingsScreen
import com.leduytuanvu.vendingmachine.features.settings.testSettings.presentation.screen.TestSettingsScreen
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.screen.HomeVendingMachineScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homeVendingMachine") {
        composable("homeVendingMachine") {
            HomeVendingMachineScreen(navController)
        }
        composable("homeSettings") {
            HomeSettingsScreen(navController)
        }
        composable("setUpProductSettings") {
            SetUpProductSettingsScreen(navController)
        }
        composable("setUpSlotSettings") {
            SetUpSlotSettingsScreen(navController)
        }
        composable("setUpSystemSettings") {
            SetUpSystemSettingsScreen(navController)
        }
        composable("testSettings") {
            TestSettingsScreen(navController)
        }
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "detail/{category}", arguments = listOf(
            navArgument("category") {
                type = NavType.StringType
            }
        )) {
            LoginScreen(navController)
        }
    }
}



