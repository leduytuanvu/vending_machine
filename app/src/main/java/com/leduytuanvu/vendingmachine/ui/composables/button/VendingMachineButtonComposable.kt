package com.leduytuanvu.vendingmachine.ui.composables.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun VendingMachineButtonComposable(
    onClick: () -> Unit,
    isCenter: Boolean = true,
    isFullSizeWidth: Boolean = true,
    text: String = "Button",
    paddingHorizontal: Int = 20,
    paddingVertical: Int = 12,
) {
    var modifierButton: Modifier = Modifier
        .clickable(onClick = onClick)
        .clip(RoundedCornerShape(6.dp))
        .background(color = Color.White, shape = RoundedCornerShape(6.dp))
        .border(
            width = 0.4.dp,
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )

    modifierButton = if (isFullSizeWidth) {
        modifierButton.fillMaxWidth()
    } else {
        modifierButton
    }

    Box(
        modifier = modifierButton,
        contentAlignment = if(isCenter) Alignment.Center else Alignment.CenterStart
    ) {
        Text(
            modifier = Modifier.padding(horizontal = paddingHorizontal.dp, vertical = paddingVertical.dp),
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(color = Color.Black),
        )
    }
}
