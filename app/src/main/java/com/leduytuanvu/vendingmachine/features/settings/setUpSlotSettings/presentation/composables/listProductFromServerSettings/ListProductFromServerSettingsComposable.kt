package com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.composables.listProductFromServerSettings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.leduytuanvu.vendingmachine.R
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.viewmodel.SetUpSlotSettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProductFromServerSettingsComposable() {
    // View model
    val listProductFromServerSettingsViewModel: ListProductFromServerSettingsViewModel = hiltViewModel()
    val setUpSlotSettingsViewModel: SetUpSlotSettingsViewModel = hiltViewModel()

    // Get list product from server
    val listProductFromServer by listProductFromServerSettingsViewModel.listProductForSale.collectAsState(initial = emptyList())
    val chunkedList = listProductFromServer.chunked(3)

    BoxWithConstraints(
        modifier = Modifier.background(Color.Black.copy(alpha = 0.7F))
    ) {
        val paddingHorizontal = maxWidth * 0.05f
        val paddingVertical = maxHeight * 0.086f
        val itemPadding = maxWidth * 0.02f
        val realWidth = maxWidth - (paddingHorizontal * 2) - (itemPadding * 2)
        val itemWidth = realWidth / 3
        val itemHeight = realWidth / 2.4f

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { setUpSlotSettingsViewModel.toggleListProductVisibility(false) }
                )
        )

        if (listProductFromServer.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp) // this shape will be used for the background
                )
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "NO PRODUCT FOUND",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }

        } else {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp) // this shape will be used for the background
                )
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(8.dp)
                ),
            ) {
                LazyColumn(modifier = Modifier
                    .padding(itemPadding)
                ) {
                    items(chunkedList.size) { index ->
                        val chunk = chunkedList[index]
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            chunk.forEach { item ->
                                Card(
                                    modifier = Modifier
                                        .width(itemWidth)
                                        .height(itemHeight)
                                        .padding(6.dp)
                                        .border(
                                            width = 1.dp,
                                            color = Color.Black,
                                            shape = RoundedCornerShape(8.dp)
                                        ),
                                    onClick = {}
                                ) {
                                    Surface(
                                        modifier = Modifier.fillMaxSize(),
                                        color = Color.White
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .aspectRatio(1f),
                                        ) {
                                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                                val interactionSource = remember { MutableInteractionSource() }
                                                Box(
                                                    modifier = Modifier
                                                        .clickable(
                                                            interactionSource = interactionSource,
                                                            indication = null,
                                                            onClick = {
                                                                Logger.info("Click on product ${item.code}")
                                                            }
                                                        )
                                                ) {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.icon_plus_product),
                                                        contentDescription = "Add product",
                                                        modifier = Modifier
                                                            .size(200.dp)
                                                            .padding(4.dp)
                                                    )
                                                }
                                                Text(
                                                    text = item.code,
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(4.dp),
                                                    style = MaterialTheme.typography.bodySmall
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}