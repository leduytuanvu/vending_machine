package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.portSetUpSystemSettings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.*
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortSetUpSystemSettingsComposable() {
    val portSetUpSystemSettingsViewModel: PortSetUpSystemSettingsViewModel = hiltViewModel()
    val valueSerialPort by portSetUpSystemSettingsViewModel.listAllPort.collectAsState(initial = "")

    val buttonModifier = Modifier
        .clip(RoundedCornerShape(6.dp))
        .background(color = Color.Red, shape = RoundedCornerShape(6.dp))
        .fillMaxWidth()

    val items = listOf("Item 1", "Item 2", "Item 3", "Item 4")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vendingMachineResponsiveDp(8f))
    ) {

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        OutlinedTextField(
            value = valueSerialPort,
            onValueChange = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(vendingMachineResponsiveDp(190f)),
            enabled = false,
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        VendingMachineButtonComposable(
            modifier = buttonModifier,
            onClick = { portSetUpSystemSettingsViewModel.getListSerialPort() },
        ) {
            Text(
                text = "Refresh",
                style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                modifier = Modifier.padding(4.dp)
            )
        }

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        Row() {
            Text(
                text = "Select port for vending machine",
                style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                modifier = Modifier.padding(4.dp)
            )

//            DropdownMenu(
//                expanded = true,
//                onDismissRequest = { }
//            ) {
//                items.forEach { label ->
//                    DropdownMenuItem(onClick = { }, text = { Text(text = label) })
//                }
//            }
        }

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        Row() {
            Text(
                text = "Select port for cash box",
                style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
