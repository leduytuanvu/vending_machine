package com.leduytuanvu.vendingmachine

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.leduytuanvu.vendingmachine.core.common.AppThemeEnums
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.navigation.AppNavigation
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.viewmodel.HomeSettingsViewModel
import com.leduytuanvu.vendingmachine.ui.theme.VendingMachineTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var portConnectionDataSource: PortConnectionDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Make app full screen
        makeAppFullScreen()

        // Open ports connection
        portConnectionDataSource.connectCashBoxPort()
        portConnectionDataSource.connectVendingMachinePort()

        // Read data from ports
        portConnectionDataSource.startReadingVendingMachine()
        portConnectionDataSource.startReadingCashBox()

        setContent {
            val homeSettingsViewModel: HomeSettingsViewModel = hiltViewModel()
            VendingMachineTheme(darkTheme = remember { homeSettingsViewModel.getTheme() == AppThemeEnums.DARK.value } ){
                AppNavigation()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Close ports connection
        portConnectionDataSource.disconnectVendingMachinePort()
        portConnectionDataSource.disconnectCashBoxPort()
        Logger.info("${javaClass.simpleName}: close all ports connection")
    }

    // Make app full screen
    private fun makeAppFullScreen() {
        val decorView = window.decorView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            decorView.windowInsetsController?.let {
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                it.hide(WindowInsets.Type.systemBars())
            }
        } else {
            @Suppress("DEPRECATION")
            decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN
            )
        }
    }
}