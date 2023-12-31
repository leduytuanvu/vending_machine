package com.leduytuanvu.vendingmachine.features.authentication.login.presentation.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.leduytuanvu.vendingmachine.features.authentication.login.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
    Surface {
        Text(text = "Login Screen")
    }
}