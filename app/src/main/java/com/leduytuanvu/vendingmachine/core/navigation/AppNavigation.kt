package com.leduytuanvu.vendingmachine.core.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leduytuanvu.vendingmachine.features.authentication.presentation.screens.LoginScreen
import com.leduytuanvu.vendingmachine.features.authentication.presentation.viewmodel.LoginViewModel
import com.leduytuanvu.vendingmachine.features.settings.home.presentation.screens.HomeSettingsScreen
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.screen.HomeVendingMachineScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeVendingMachineScreen(navController)
        }
        composable("settings") {
            HomeSettingsScreen(navController)
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



