package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.adsVendingMachine

import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.leduytuanvu.vendingmachine.ui.composables.button.VendingMachineButtonComposable

//@Composable
//fun AdsVendingMachineComposable(onTurnOffClick: () -> Unit) {
//
//    val viewModel: AdsVendingMachineViewModel = hiltViewModel()
//    val context = LocalContext.current
//    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
//    var currentVideoIndex by remember { mutableStateOf(0) }
//    // Assume the video has an aspect ratio of 16:9, adjust if you have a different aspect ratio
//    val videoAspectRatio = 16f / 9f
//    val videoHeight = screenWidth / videoAspectRatio
//
//    Box (modifier = Modifier
//        .height(videoHeight)
//        .fillMaxWidth()
//        .background(Color.Black)) {
//        AndroidView(
//            modifier = Modifier.fillMaxSize(),
//            factory = {
//                VideoView(context).apply {
//                    setOnCompletionListener {
//                        // Play next video when the current one completes
//                        currentVideoIndex = (currentVideoIndex + 1) % viewModel.listAds.size
//                        setVideoPath(viewModel.listAds[currentVideoIndex])
//                        start()
//                    }
//                }
//            },
//            update = { videoView ->
//                if (!videoView.isPlaying) {
//                    videoView.setVideoPath(viewModel.listAds[currentVideoIndex])
//                    videoView.start()
//                }
//            }
//        )
//
//        VendingMachineButtonComposable(
//            onClick = { onTurnOffClick() },
//            modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 8.dp)
//                .clip(RoundedCornerShape(topStart = 6.dp, bottomStart = 6.dp))
//                .background(color = Color.Red, shape = RoundedCornerShape(topStart = 6.dp, bottomStart = 6.dp))
//        ) {
//            Text(text = "Turn off", color = Color.White)
//        }
//    }
//}

@Composable
fun AdsVendingMachineComposable(onTurnOffClick: () -> Unit) {

    val viewModel: AdsVendingMachineViewModel = hiltViewModel()
    val context = LocalContext.current
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    var currentVideoIndex by remember { mutableStateOf(0) }
    val videoAspectRatio = 16f / 9f
    val videoHeight = screenWidth / videoAspectRatio
    val videoView = remember { mutableStateOf<VideoView?>(null) }

    Box(
        modifier = Modifier
            .height(videoHeight)
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        Box(modifier = Modifier.fillMaxSize().background(Color.White))
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                VideoView(context).apply {
                    setBackgroundColor(Color.Transparent.toArgb())
                    setOnCompletionListener {
                        currentVideoIndex = (currentVideoIndex + 1) % viewModel.listAds.size
                        setVideoPath(viewModel.listAds[currentVideoIndex])
                        start()
                    }
                    videoView.value = this // Assign the current VideoView instance to the mutableStateOf
                }
            },
            update = { view ->
                // Update the view if needed when the composable function is recomposed
            }
        )

        DisposableEffect(Unit) {
            onDispose {
                // Stop video playback when the composable leaves the composition
                videoView.value?.stopPlayback()
            }
        }

        VendingMachineButtonComposable(
            onClick = { onTurnOffClick() },
            text = "urn off"
        )
    }
}
