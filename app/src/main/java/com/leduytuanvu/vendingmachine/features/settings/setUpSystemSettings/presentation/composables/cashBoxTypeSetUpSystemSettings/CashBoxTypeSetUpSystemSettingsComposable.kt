package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.cashBoxTypeSetUpSystemSettings

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable
import com.leduytuanvu.vendingmachine.ui.composables.dropdown.VendingMachineDropdownComposable
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CashBoxTypeSetUpSystemSettingsComposable() {
    val cashBoxTypeSetUpSystemSettingsViewModel: CashBoxTypeSetUpSystemSettingsViewModel = hiltViewModel()
    val selectedCashBoxTypeItem = cashBoxTypeSetUpSystemSettingsViewModel.selectedCashBoxTypeItem

    val buttonModifier = Modifier
        .clip(RoundedCornerShape(6.dp))
        .background(color = Color.Red, shape = RoundedCornerShape(6.dp))
        .fillMaxWidth()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vendingMachineResponsiveDp(20f))
    ) {
        Text(
            text = "Select type vending machine",
            style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
            modifier = Modifier.padding(4.dp)
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        VendingMachineDropdownComposable(
            items = cashBoxTypeSetUpSystemSettingsViewModel.listTypeCashBox,
            selectedItem = selectedCashBoxTypeItem, // Use directly
            onItemSelected = { item ->
                cashBoxTypeSetUpSystemSettingsViewModel.updateSelectCashBoxTypeItem(item)
            }
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        VendingMachineButtonComposable(
            onClick = { cashBoxTypeSetUpSystemSettingsViewModel.save() },
            text = "Save"
        )
    }
}