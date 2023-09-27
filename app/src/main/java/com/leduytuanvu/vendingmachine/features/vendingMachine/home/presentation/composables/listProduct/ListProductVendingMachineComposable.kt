package com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.listProduct

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.ads.AdsVendingMachineViewModel

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import com.leduytuanvu.vendingmachine.features.vendingMachine.home.data.models.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProductVendingMachineComposable(modifier: Modifier = Modifier) {
    val viewModel: AdsVendingMachineViewModel = hiltViewModel()
    val productList = listOf(
        Product("Product 1"),
        Product("Product 2"),
        Product("Product 3"),
        Product("Product 4"),
        Product("Product 1"),
        Product("Product 2"),
        Product("Product 3"),
        Product("Product 4"),
        Product("Product 1"),
        Product("Product 2"),
        Product("Product 3"),
        Product("Product 4")
    )
    val chunkedList = productList.chunked(2)

    LazyColumn(modifier = modifier) {
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
                                    text = item.name,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

