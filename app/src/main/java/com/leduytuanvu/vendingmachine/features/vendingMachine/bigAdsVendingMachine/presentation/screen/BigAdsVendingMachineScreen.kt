package com.leduytuanvu.vendingmachine.features.vendingMachine.bigAdsVendingMachine.presentation.screen

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.VideoView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.leduytuanvu.vendingmachine.features.vendingMachine.bigAdsVendingMachine.presentation.viewmodel.BigAdsVendingMachineViewModel
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.adsVendingMachine.AdsVendingMachineViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BigAdsVendingMachineScreen() {

    val adsViewModel: AdsVendingMachineViewModel = hiltViewModel()
    val bigAdsViewModel: BigAdsVendingMachineViewModel = hiltViewModel()
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    var currentVideoIndex by remember { mutableStateOf(0) }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = {
                    VideoView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        setOnCompletionListener {
                            // Play next video when the current one completes
                            currentVideoIndex = (currentVideoIndex + 1) % adsViewModel.listAds.size
                            setVideoPath(adsViewModel.listAds[currentVideoIndex])
                            start()
                        }
                    }
                },
                update = { videoView ->
                    if (!videoView.isPlaying) {
                        videoView.setVideoPath(adsViewModel.listAds[currentVideoIndex])
                        videoView.start()
                    }
                }
            )
        }
    }
}
