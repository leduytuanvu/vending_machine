package com.leduytuanvu.vendingmachine.features.splash.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.splash.presentation.viewmodel.SplashViewModel
import com.leduytuanvu.vendingmachine.ui.composables.loading.VendingMachineCircularProgessIndicator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(navController: NavController) {

    val splashViewModel: SplashViewModel = hiltViewModel()
    val navigateTo by splashViewModel.navigateTo.collectAsState()

    if (navigateTo != null) {
        navController.navigate(navigateTo!!) {
            popUpTo("splash") { inclusive = true }
        }
        // Reset navigation event to avoid re-navigation on recomposition
        splashViewModel.resetNavigationEvent()
    }

    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            VendingMachineCircularProgessIndicator()
        }
    }
}
