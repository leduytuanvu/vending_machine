package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.heatingSetUpSystemSettings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.portSetUpSystemSettings.PortSetUpSystemSettingsViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeatingSetUpSystemSettingsComposable() {
    val portSetUpSystemSettingsViewModel: PortSetUpSystemSettingsViewModel = hiltViewModel()
    val valueSerialPort by portSetUpSystemSettingsViewModel.listAllPort.collectAsState(initial = "")

    val buttonModifier = Modifier
        .clip(RoundedCornerShape(6.dp))
        .background(color = Color.Red, shape = RoundedCornerShape(6.dp))
        .fillMaxWidth()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vendingMachineResponsiveDp(8f))
    ) {

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        Text(text = "HEATING")
    }
}