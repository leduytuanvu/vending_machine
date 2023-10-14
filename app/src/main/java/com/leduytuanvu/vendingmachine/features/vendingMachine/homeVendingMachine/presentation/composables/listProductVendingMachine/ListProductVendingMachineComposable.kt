package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.listProductVendingMachine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.adsVendingMachine.AdsVendingMachineViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.viewmodel.HomeVendingMachineViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProductVendingMachineComposable(onTurnOnClick: () -> Unit) {
    val adsVendingMachineViewModel: AdsVendingMachineViewModel = hiltViewModel()
    val homeVendingMachineViewModel: HomeVendingMachineViewModel = hiltViewModel()
    val listProductVendingMachineViewModel: ListProductVendingMachineViewModel = hiltViewModel()

    val listProductForSale by listProductVendingMachineViewModel.listOfProductForSale.collectAsState(initial = emptyList())
    val chunkedList = listProductForSale.chunked(3)

    Box (modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            item {
                Spacer(modifier = Modifier.height(18.dp))
            }
            items(chunkedList.size) { index ->
                val chunk = chunkedList[index]
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    chunk.forEach { item ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp),
                            onClick = {}
                        ) {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = Color.Gray
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f)
                                ) {
                                    Text(
                                        text = item.product_name,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (!homeVendingMachineViewModel.isAdsVisible.value) {
            VendingMachineButtonComposable(
                onClick = { onTurnOnClick() },
                text = "Turn on"
            )
        }

        VendingMachineButtonComposable(
            onClick = {  },
            text = "50.000 vnd"
        )
    }
}

