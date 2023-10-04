package com.leduytuanvu.vendingmachine.features.settings.testSettings.presentation.screen

import android.R.attr.singleLine
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.settings.homeSettings.presentation.viewmodel.HomeSettingsViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestSettingsScreen(navController: NavController) {
    val viewModel: HomeSettingsViewModel = hiltViewModel()

    val buttonModifier = Modifier
        .clip(RoundedCornerShape(6.dp))
        .height(50.dp)
        .background(color = Color.Red, shape = RoundedCornerShape(6.dp))
        .fillMaxWidth()

    val textModifier = Modifier.padding(top = 4.dp, bottom = 4.dp)

    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Test", style = MaterialTheme.typography.titleMedium)
                    }
                },
                actions = {
                    Spacer(modifier = Modifier.width(48.dp))
                },
                modifier = Modifier
                    .height(50.dp)
                    .background(Color.Blue),
            )
        }
    ) {
        innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        Column(modifier = modifier.background(Color.White)) {

            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text("Non-Scrollable Box", modifier = Modifier.align(Alignment.Center))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f)
                    .padding(8.dp)
            ) {
                Text(text = "Reset slot")
                Spacer(modifier = Modifier.height(8.dp))
                VendingMachineButtonComposable(
                    onClick = { navController.navigate("setUpSystemSettings") },
                    modifier = buttonModifier
                ) {
                    Text(modifier = textModifier, text = "Reset", style = MaterialTheme.typography.titleSmall)
                }
                Spacer(modifier = Modifier.height(8.dp))


                Text(text = "Set slot mode")
                Spacer(modifier = Modifier.height(8.dp))
                Row() {
                    BasicTextField(
                        value = "text",
                        onValueChange = {  },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .background(Color.Transparent) // Sets the background color of the TextField.
                            .border(1.dp, Color.Black, RoundedCornerShape(6.dp)) // Sets the border around the TextField.
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    VendingMachineButtonComposable(
                        onClick = { navController.navigate("setUpSystemSettings") },
                        modifier = buttonModifier.weight(2f)
                    ) {
                        Text(modifier = textModifier, text = "Set one slot as belt slot", style = MaterialTheme.typography.titleSmall)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                VendingMachineButtonComposable(
                    onClick = { navController.navigate("setUpSystemSettings") },
                    modifier = buttonModifier
                ) {
                    Text(modifier = textModifier, text = "Set all slot as belt slot", style = MaterialTheme.typography.titleSmall)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row() {
                    BasicTextField(
                        value = "text",
                        onValueChange = {  },
                        modifier = Modifier
                            .weight(1f)
                            .height(50.dp)
                            .background(Color.Transparent) // Sets the background color of the TextField.
                            .border(1.dp, Color.Black, RoundedCornerShape(6.dp)) // Sets the border around the TextField.
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    VendingMachineButtonComposable(
                        onClick = { navController.navigate("setUpSystemSettings") },
                        modifier = buttonModifier.weight(2f)
                    ) {
                        Text(modifier = textModifier, text = "Set one slot as spiral slot", style = MaterialTheme.typography.titleSmall)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                VendingMachineButtonComposable(
                    onClick = { navController.navigate("setUpSystemSettings") },
                    modifier = buttonModifier
                ) {
                    Text(modifier = textModifier, text = "Set all slot as spiral slot", style = MaterialTheme.typography.titleSmall)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = "Light control")
                Spacer(modifier = Modifier.height(8.dp))
                VendingMachineButtonComposable(
                    onClick = { navController.navigate("setUpSystemSettings") },
                    modifier = buttonModifier
                ) {
                    Text(modifier = textModifier, text = "Turn on", style = MaterialTheme.typography.titleSmall)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}