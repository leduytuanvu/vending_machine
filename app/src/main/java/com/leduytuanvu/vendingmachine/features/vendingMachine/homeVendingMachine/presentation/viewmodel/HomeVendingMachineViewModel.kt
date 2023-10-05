package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.core.common.AppByteArrays
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeVendingMachineViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    private val portConnectionDataSource: PortConnectionDataSource
) : ViewModel() {

    val isAdsVisible = mutableStateOf(true)

    val dataFromVendingMachine: StateFlow<String> = portConnectionDataSource.dataFromVendingMachine
    val dataFromCashBox: StateFlow<String> = portConnectionDataSource.dataFromCashBox

    fun hideVideoAds() {
        isAdsVisible.value = false
    }

    fun showVideoAds() {
        isAdsVisible.value = true
    }

    fun writeByteArrayToPort() {
        val bytesWritten = portConnectionDataSource.sendCommandVendingMachine(AppByteArrays.vendingMachineTurnOnLight)
        val bytesWritten2 = portConnectionDataSource.sendCommandCashBox(AppByteArrays.cashBoxReset)
        if (bytesWritten == -1) {
            Logger.error("Error writing to port")
        }
    }
}