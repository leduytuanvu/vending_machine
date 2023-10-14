package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.portSetUpSystemSettings

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.*
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable
import com.leduytuanvu.vendingmachine.ui.composables.dropdown.VendingMachineDropdownComposable
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortSetUpSystemSettingsComposable(isVisible: Boolean) {
    val portSetUpSystemSettingsViewModel: PortSetUpSystemSettingsViewModel = hiltViewModel()
    val valueSerialPort by portSetUpSystemSettingsViewModel.listAllPort.collectAsState(initial = "")

    val dropdownItems by portSetUpSystemSettingsViewModel.listDropDownPortFlow.collectAsState(initial = emptyList())

    val selectedPortVendingMachineItem = portSetUpSystemSettingsViewModel.selectedPortVendingMachineItem
    val selectedPortCashBoxItem = portSetUpSystemSettingsViewModel.selectedPortCashBoxItem
    val selectedBaudRateCashBoxItem = portSetUpSystemSettingsViewModel.selectedBaudRateCashBoxItem
    val selectedBaudRateVendingMachineItem = portSetUpSystemSettingsViewModel.selectedBaudRateVendingMachineItem
    val selectedProtocolTypeVendingMachineItem = portSetUpSystemSettingsViewModel.selectedProtocolTypeVendingMachineItem
    val selectedProtocolTypeCashBoxItem = portSetUpSystemSettingsViewModel.selectedProtocolTypeCashBoxItem

    val currentSelected by portSetUpSystemSettingsViewModel.currentSelected.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vendingMachineResponsiveDp(8f))
        .verticalScroll(rememberScrollState())
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

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        VendingMachineButtonComposable(
            onClick = { portSetUpSystemSettingsViewModel.getListSerialPort() },
            text = "Refresh"
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(30f)))

        Column(
            modifier = Modifier
            .border(0.6.dp, Color.LightGray, RoundedCornerShape(4.dp))
            .padding(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .border(0.6.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .padding(4.dp)
            ) {
                val cashBoxBorder = animateDpAsState(
                    targetValue = if (currentSelected == "CASH BOX") 1.dp else 0.dp
                ).value
                val cashBoxBorderColor = animateColorAsState(
                    targetValue = if (currentSelected == "CASH BOX") Color.Gray else Color.Transparent
                ).value
                val cashBoxBackground = animateColorAsState(
                    targetValue = if (currentSelected == "CASH BOX") Color.LightGray else Color.White
                ).value

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(cashBoxBorder, cashBoxBorderColor, RoundedCornerShape(4.dp))
                        .background(cashBoxBackground)
                        .clickable {
                            portSetUpSystemSettingsViewModel.selectItem("CASH BOX")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CASH BOX",
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                val vendingMachineBorder = animateDpAsState(
                    targetValue = if (currentSelected == "VENDING MACHINE") 1.dp else 0.dp
                ).value
                val vendingMachineBorderColor = animateColorAsState(
                    targetValue = if (currentSelected == "VENDING MACHINE") Color.Gray else Color.Transparent
                ).value
                val vendingMachineBackground = animateColorAsState(
                    targetValue = if (currentSelected == "VENDING MACHINE") Color.LightGray else Color.White
                ).value

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            vendingMachineBorder,
                            vendingMachineBorderColor,
                            RoundedCornerShape(4.dp)
                        )
                        .background(vendingMachineBackground)
                        .clickable {
                            portSetUpSystemSettingsViewModel.selectItem("VENDING MACHINE")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "VENDING MACHINE",
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            when (currentSelected) {
                "CASH BOX" -> Column(modifier = Modifier.padding(30.dp)) {

                    Text(
                        text = "Select the connection protocol type",
                        style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                        modifier = Modifier.padding(4.dp)
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(8f)))

                    VendingMachineDropdownComposable(
                        items = portSetUpSystemSettingsViewModel.listProtocolType,
                        selectedItem = selectedProtocolTypeCashBoxItem, // Use directly
                        onItemSelected = { item ->
                            portSetUpSystemSettingsViewModel.updateSelectProtocolTypeCashBoxItem(item)
                        }
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(16f)))

                    Text(
                        text = "Select port",
                        style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                        modifier = Modifier.padding(4.dp)
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(8f)))

                    VendingMachineDropdownComposable(
                        items = dropdownItems,
                        selectedItem = selectedPortCashBoxItem, // Use directly
                        onItemSelected = { item ->
                            portSetUpSystemSettingsViewModel.updateSelectPortCashBoxItem(item)
                        }
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(16f)))

                    Text(
                        text = "Select baud rate",
                        style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                        modifier = Modifier.padding(4.dp)
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(8f)))

                    VendingMachineDropdownComposable(
                        items = portSetUpSystemSettingsViewModel.listBaudRate,
                        selectedItem = selectedBaudRateCashBoxItem,
                        onItemSelected = { item ->
                            portSetUpSystemSettingsViewModel.updateSelectBaudRateCashBoxItem(item)
                        }
                    )
                }
                "VENDING MACHINE" -> Column(modifier = Modifier.padding(30.dp)) {

                    Text(
                        text = "Select the connection protocol type",
                        style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                        modifier = Modifier.padding(4.dp)
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(8f)))

                    VendingMachineDropdownComposable(
                        items = portSetUpSystemSettingsViewModel.listProtocolType,
                        selectedItem = selectedProtocolTypeVendingMachineItem, // Use directly
                        onItemSelected = { item ->
                            portSetUpSystemSettingsViewModel.updateSelectPortVendingMachineItem(item)
                        }
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(16f)))

                    Text(
                        text = "Select port",
                        style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                        modifier = Modifier.padding(4.dp)
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(8f)))

                    VendingMachineDropdownComposable(
                        items = dropdownItems,
                        selectedItem = selectedPortVendingMachineItem, // Use directly
                        onItemSelected = { item ->
                            portSetUpSystemSettingsViewModel.updateSelectProtocolTypeVendingMachineItem(item)
                        }
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(16f)))

                    Text(
                        text = "Select baud rate",
                        style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
                        modifier = Modifier.padding(4.dp)
                    )

                    Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(8f)))

                    VendingMachineDropdownComposable(
                        items = portSetUpSystemSettingsViewModel.listBaudRate,
                        selectedItem = selectedBaudRateVendingMachineItem, // Use directly
                        onItemSelected = { item ->
                            portSetUpSystemSettingsViewModel.updateSelectBaudRateVendingMachineItem(item)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        VendingMachineButtonComposable(
            onClick = { portSetUpSystemSettingsViewModel.savePort() },
            text = "Save"
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        VendingMachineButtonComposable(
            onClick = { portSetUpSystemSettingsViewModel.turnOnLight() },
            text = "Turn on light"
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        VendingMachineButtonComposable(
            onClick = { portSetUpSystemSettingsViewModel.turnOffLight() },
            text = "Turn off light"
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        VendingMachineButtonComposable(
            onClick = { portSetUpSystemSettingsViewModel.enableCashBox() },
            text = "Enable cash box"
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        VendingMachineButtonComposable(
            onClick = { portSetUpSystemSettingsViewModel.resetCashBox() },
            text = "Reset cash box"
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))
    }
}
