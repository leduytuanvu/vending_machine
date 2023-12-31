package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.portSetUpSystemSettings.PortSetUpSystemSettingsComposable
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.portSetUpSystemSettings.PortSetUpSystemSettingsViewModel
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.viewmodel.SetUpSystemSettingsViewModel
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpSystemSettingsScreen(navController: NavController) {
    val portSetUpSystemSettingsViewModel: PortSetUpSystemSettingsViewModel = hiltViewModel()
    val isLoading by portSetUpSystemSettingsViewModel.setUpSystemSettingsRepositoryImpl.isLoading.collectAsState()

    val setUpSystemSettingsViewModel: SetUpSystemSettingsViewModel = hiltViewModel()
    val currentSelected by setUpSystemSettingsViewModel.currentSelected.collectAsState()

    Box {
        Scaffold (
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            IconButton(
                                onClick = { navController.navigateUp() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null, tint = Color.White
                                )
                            }
                        }
                    },
                    title = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "SET UP SYSTEM",
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                    },
                    actions = {
                        Spacer(
                            modifier = Modifier.width(vendingMachineResponsiveDp(40f))
                        )
                    },
                    modifier = Modifier
                        .height(vendingMachineResponsiveDp(52f))
                        .background(Color.Blue),
                )
            }
        ) {
            innerPadding ->
            val modifier = Modifier.padding(innerPadding)
            Column(modifier = modifier.background(Color.White)) {
                LazyRow {
                    items(setUpSystemSettingsViewModel.itemsList.size) { index ->
                        Box(modifier = Modifier
                            .background(if (currentSelected == setUpSystemSettingsViewModel.itemsList[index]) Color.LightGray else Color.White)
                            .clickable {
                                setUpSystemSettingsViewModel.selectItem(
                                    setUpSystemSettingsViewModel.itemsList[index]
                                )
                            }
                        ) {
                            Text(
                                text = setUpSystemSettingsViewModel.itemsList[index],
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
                when (currentSelected) {
                    "PORT" -> PortSetUpSystemSettingsComposable()
                    "LIGHT" -> Text("Content of Light")
                }
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.7F))
                    .clickable(enabled = false) {},
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
        }
    }


}