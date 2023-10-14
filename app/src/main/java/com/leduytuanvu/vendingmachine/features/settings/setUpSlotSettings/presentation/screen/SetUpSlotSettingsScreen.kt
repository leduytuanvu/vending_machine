package com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.viewmodel.SetUpSlotSettingsViewModel
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable
import com.leduytuanvu.vendingmachine.ui.composables.toast.VendingMachineToast
import com.leduytuanvu.vendingmachine.ui.theme.vendingMachineResponsiveDp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.leduytuanvu.vendingmachine.R
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.composables.listProductFromServerSettings.ListProductFromServerSettingsComposable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetUpSlotSettingsScreen(navController: NavController) {
    val setUpSlotSettingsViewModel: SetUpSlotSettingsViewModel = hiltViewModel()

    val isLoadingSetUpSlotSettingsViewModel by setUpSlotSettingsViewModel.isLoading.collectAsState()
    val isLoading =isLoadingSetUpSlotSettingsViewModel

    val showToastSetUpSlotSettingsViewModel by setUpSlotSettingsViewModel.showToast.collectAsState()
    val showToast = showToastSetUpSlotSettingsViewModel

    val listProductForSale by setUpSlotSettingsViewModel.listProductForSale.collectAsState(initial = emptyList())
    val chunkedList = listProductForSale.chunked(3)

    // Observe the visibility state from the ViewModel
    val isListProductFromServerVisible by setUpSlotSettingsViewModel.isListProductFromServerVisible.collectAsState()

    val buttonModifier = Modifier
        .clip(RoundedCornerShape(6.dp))
        .border(
            width = 1.dp,
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        )
        .background(color = Color.White, shape = RoundedCornerShape(6.dp))

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
                                text = "SET UP SLOT",
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { // This will center its children
                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    val padding = maxWidth * 0.016f
                    val itemWidth = (maxWidth - padding * 2) / 3
                    val itemHeight = itemWidth * 1.5f
                    // Use a Column to stack the non-scrolling and scrolling content
                    Column(modifier = Modifier
                        .background(Color.White)
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            start = padding,
                            end = padding,
                        ),
                    ) {
                        // This is your non-scrolling content
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = padding, bottom = padding),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // First Button
                            VendingMachineButtonComposable(
                                onClick = { /*TODO: Your action here*/ },
                                text = "RESET SLOT"
                            )
                            // Add spacing between buttons if needed
                            Spacer(modifier = Modifier.width(8.dp))
                            // Second Button
                            VendingMachineButtonComposable(
                                onClick = { /*TODO: Your action here*/ },
                                text = "SOMETHING ELSE"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        // This Box will contain the scrollable content
                        Box () {  // weight(1f) makes it fill the remaining space
                            LazyColumn(modifier = Modifier.fillMaxSize()) {
                                items(chunkedList.size) { index ->
                                    val chunk = chunkedList[index]
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Start, // change here for even spacing
                                    ) {
                                        chunk.forEach { item ->
                                            Card(
                                                modifier = Modifier
                                                    .width(itemWidth)
                                                    .height(itemHeight)
                                                    .padding(
                                                        start = 12.dp,
                                                        end = 12.dp,
                                                        bottom = 24.dp
                                                    )
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
                                                            .fillMaxWidth(),
                                                    ) {
                                                        Column(
                                                            horizontalAlignment = Alignment.CenterHorizontally,
                                                            verticalArrangement = Arrangement.Top,
                                                            modifier = Modifier
                                                                .fillMaxSize() // This ensures the Column fills its parent
                                                                .padding(4.dp),
                                                        ) {
                                                            val interactionSource = remember { MutableInteractionSource() }
                                                            Row(
                                                                modifier = Modifier.fillMaxWidth() // This will make the row fill the entire width
                                                            ) {
                                                                Text(
                                                                    text = item.slotOnDevice.toString(),
                                                                    modifier = Modifier
                                                                        .weight(1f) // This makes the text occupy the majority of the space
                                                                        .padding(
                                                                            start = 16.dp,
                                                                            top = 8.dp
                                                                        ),
                                                                    style = MaterialTheme.typography.titleLarge,
                                                                    color = Color.Black // Assuming you want white text on a black background
                                                                )
                                                                Image(
                                                                    painter = painterResource(id = R.drawable.icon_un_check_box),
                                                                    contentDescription = "Uncheck box icon",
                                                                    modifier = Modifier
                                                                        .size(56.dp)
                                                                        .padding(
                                                                            top = 8.dp,
                                                                            end = 8.dp
                                                                        )
                                                                    // No weight added here, so the image occupies only its needed space
                                                                )
                                                            }
                                                            Box(
                                                                modifier = Modifier
                                                                    .clickable(
                                                                        interactionSource = interactionSource,
                                                                        indication = null,
                                                                        onClick = {
                                                                            setUpSlotSettingsViewModel.addProductForSale(item)
                                                                        }
                                                                    )
                                                            ) {
                                                                Image(
                                                                    painter = painterResource(id = R.drawable.icon_plus_product),
                                                                    contentDescription = "Add product",
                                                                    modifier = Modifier
                                                                        .size(220.dp)
                                                                        .padding(4.dp)
                                                                )
                                                            }

                                                            Spacer(modifier = Modifier.height(22.dp))

                                                            Text(
                                                                text = "Not have any products",
                                                                modifier = Modifier
                                                                    .fillMaxWidth() // This will make the text fill the maximum width
                                                                    .padding(4.dp),
                                                                style = MaterialTheme.typography.bodyLarge,
                                                                color = Color.Black, // Adjust text color as per your design
                                                                textAlign = TextAlign.Center // This centers the text
                                                            )

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
                                    if (chunkedList.size - 1 == index) {
                                        Spacer(modifier = Modifier.height(padding - 6.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (isListProductFromServerVisible) {
            ListProductFromServerSettingsComposable()
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
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }

        if (showToast) {
            VendingMachineToast(
                message = "Load product success",
                onDismiss = { setUpSlotSettingsViewModel.setShowToastState(false) }
            )
        }
    }
}