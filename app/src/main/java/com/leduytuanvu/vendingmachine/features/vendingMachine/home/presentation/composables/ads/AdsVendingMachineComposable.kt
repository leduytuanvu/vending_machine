package com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.ads

import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.Color
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButton

@Composable
fun AdsVendingMachineComposable() {

    val viewModel: AdsVendingMachineViewModel = hiltViewModel()
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var currentVideoIndex by remember { mutableStateOf(0) }
    // Assume the video has an aspect ratio of 16:9, adjust if you have a different aspect ratio
    val videoAspectRatio = 16f / 9f
    val videoHeight = screenWidth / videoAspectRatio

    Box (modifier = Modifier.height(videoHeight).fillMaxWidth().background(Color.Black)) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                VideoView(context).apply {
                    setOnCompletionListener {
                        // Play next video when the current one completes
                        currentVideoIndex = (currentVideoIndex + 1) % viewModel.listAds.size
                        setVideoPath(viewModel.listAds[currentVideoIndex])
                        start()
                    }
                }
            },
            update = { videoView ->
                if (!videoView.isPlaying) {
                    videoView.setVideoPath(viewModel.listAds[currentVideoIndex])
                    videoView.start()
                }
            }
        )

        // Overlaying a Button on the VideoView
        Button(
            modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp),
            onClick = {
                viewModel.turnOffAds()
            }
        ) {
            Text("Turn off ads")
        }

        VendingMachineButton(
            onClick = { /* Handle Click */ },
            backgroundColor = Color.Red,
            borderRadius = 16.dp
        ) {
            Text(text = "Click Me", color = Color.White)
        }
    }
}