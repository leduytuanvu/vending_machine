package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.R
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.adsVendingMachine.AdsVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.informationVendingMachine.InformationVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.listProductVendingMachine.ListProductVendingMachineComposable
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.statusBarVendingMachine.StatusBarVendingMachineComposable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.viewmodel.HomeVendingMachineViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeVendingMachineScreen(navController: NavController) {
    val homeVendingMachineViewModel: HomeVendingMachineViewModel = hiltViewModel()

    // Collecting the data emitted by StateFlow objects
    val dataFromTTYS3 by homeVendingMachineViewModel.dataFromVendingMachine.collectAsState(initial = "")
    val dataFromTTYS4 by homeVendingMachineViewModel.dataFromCashBox.collectAsState(initial = "")

    Scaffold {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val maxHeight = constraints.maxHeight.dp
            Image(
                painter = painterResource(id = R.drawable.background_home),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Box(modifier = Modifier.clipToBounds()) {
                Column(
                    modifier = Modifier
                        .background(Color.Transparent)
                        .height(maxHeight)
                ) {
                    if (homeVendingMachineViewModel.isAdsVisible.value) {
                        AdsVendingMachineComposable(
                            onTurnOffClick = { homeVendingMachineViewModel.hideVideoAds() }
                        )
                    }

                    StatusBarVendingMachineComposable()
                    Text("Data from TTYS3: $dataFromTTYS3")
                    Text("Data from TTYS4: $dataFromTTYS4")
                    VendingMachineButtonComposable(
                        onClick = { homeVendingMachineViewModel.writeByteArrayToPort() },
                        text = "GET"
                    )
                    InformationVendingMachineComposable(navController)
                    ListProductVendingMachineComposable(
                        onTurnOnClick = { homeVendingMachineViewModel.showVideoAds() }
                    )
                }
            }
        }
    }
}

