package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.lightSetUpSystemSettings

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
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightSetUpSystemSettingsComposable() {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(vendingMachineResponsiveDp(20f))
    ) {
        Text(
            text = "Turn on light",
            style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
            modifier = Modifier.padding(4.dp)
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        VendingMachineButtonComposable(
            onClick = {  },
            text = "Turn on"
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        Text(
            text = "Turn off light",
            style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
            modifier = Modifier.padding(4.dp)
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        VendingMachineButtonComposable(
            onClick = {  },
            text = "Turn off"
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(20f)))

        Text(
            text = "Set light on time",
            style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
            modifier = Modifier.padding(4.dp)
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))

        Text(
            text = "Set light off time",
            style = MaterialTheme.typography.bodyLarge.copy(Color.Black),
            modifier = Modifier.padding(4.dp)
        )

        Spacer(modifier = Modifier.height(vendingMachineResponsiveDp(10f)))
    }
}