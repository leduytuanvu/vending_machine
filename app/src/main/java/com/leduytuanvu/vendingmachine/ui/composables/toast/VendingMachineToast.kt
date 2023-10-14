package com.leduytuanvu.vendingmachine.ui.composables.toast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VendingMachineToast(
    message: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    backgroundColor: Color = Color.LightGray,
    onDismiss: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000L) // display toast for 3 seconds
        onDismiss()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .height(250.dp)
                .width(250.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Text(
                text = message,
                color = color,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(50.dp),
            )
        }
    }
}
