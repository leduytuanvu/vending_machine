package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.portSetUpSystemSettings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leduytuanvu.vendingmachine.core.common.AppByteArrays
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.repositoryImpl.SetUpSystemSettingsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.timeout
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class PortSetUpSystemSettingsViewModel @Inject constructor(
    private val portConnectionDataSource: PortConnectionDataSource,
    val setUpSystemSettingsRepositoryImpl: SetUpSystemSettingsRepositoryImpl,
) : ViewModel() {

    private val _listAllPort = MutableStateFlow<String>("")
    val listAllPort: StateFlow<String> = _listAllPort

    val dataFromVendingMachine: StateFlow<String> = portConnectionDataSource.dataFromVendingMachine
    val dataFromCashBox: StateFlow<String> = portConnectionDataSource.dataFromCashBox

    val timeout = 3000.milliseconds

    init {
        getListSerialPort()
    }

    @OptIn(FlowPreview::class)
    suspend fun waitForDataFromCashBox(): String {
        return dataFromCashBox
            .filter { it.isNotBlank() } // Wait for non-blank data
            .onEach { Logger.info("Received data from cash box: $it") }
            .timeout(timeout) // If no data after the specified time, throw a TimeoutCancellationException
            .first() // Take the first non-blank data
    }

    @OptIn(FlowPreview::class)
    suspend fun waitForDataFromVendingMachine(): String {
        return dataFromVendingMachine
            .filter { it.isNotBlank() }
            .onEach { Logger.info("Received data from vending machine: $it") }
            .timeout(timeout)
            .first()
    }

    fun getListSerialPort() = viewModelScope.launch {
        setUpSystemSettingsRepositoryImpl.setLoading(true)
        val list = portConnectionDataSource.getAllSerialPorts()
        if (list.isEmpty()) {
            _listAllPort.value = "NOT HAVE ANY PORT"
            return@launch
        }
        processPorts(list)
        setUpSystemSettingsRepositoryImpl.setLoading(false)
    }

    private suspend fun processPorts(ports: Array<String>) {
        ports.forEachIndexed { index, port ->
            portConnectionDataSource.makeDataEmpty()
            val status = getPortStatus(port)
            _listAllPort.value += "[${index + 1}] $port : $status\n"
        }
    }

    private suspend fun getPortStatus(port: String): String {
        portConnectionDataSource.disconnectCashBoxPort()
        val statusConnectCashBox = portConnectionDataSource.checkConnectPortCashBox(port)
        if (statusConnectCashBox == -1) {
            return "is not available"
        }

        portConnectionDataSource.sendCommandCashBox(AppByteArrays.cashBoxPollStatus)
        try {
            waitForDataFromCashBox()
            return "is being connected"
        } catch (e: TimeoutCancellationException) {
            // Handle the timeout case if needed
        }

        portConnectionDataSource.disconnectVendingMachinePort()
        val statusConnectVendingMachine = portConnectionDataSource.checkConnectPortVendingMachine(port)
        if (statusConnectVendingMachine == -1) {
            return "not available"
        }

        portConnectionDataSource.sendCommandVendingMachine(AppByteArrays.vendingMachineTurnOnLight)
        try {
            waitForDataFromVendingMachine()
            return "is being connected"
        } catch (e: TimeoutCancellationException) {
            // Handle the timeout case if needed
        }

        return "is available"
    }
}