package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.portSetUpSystemSettings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leduytuanvu.vendingmachine.base.VendingMachineViewModel
import com.leduytuanvu.vendingmachine.core.common.AppByteArrays
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.enums.PortStatus
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.enums.PortType
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.enums.ProtocolType
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.cashBox.CashBoxPort
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.port.Port
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.vendingMachine.VendingMachinePort
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.timeout
import java.lang.reflect.Type
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
class PortSetUpSystemSettingsViewModel @Inject constructor(
    private val portConnectionDataSource: PortConnectionDataSource,
    private val storageDataSource: StorageDataSource,
) : VendingMachineViewModel() {

    // Timeout for waiting response from board
    val timeout = 3000.milliseconds

    // Data receive
    val dataFromVendingMachine: StateFlow<String> = portConnectionDataSource.dataFromVendingMachine
    val dataFromCashBox: StateFlow<String> = portConnectionDataSource.dataFromCashBox

    // List port get from board
    private val _listAllPort = MutableStateFlow<ArrayList<Port>>(arrayListOf())
    val listAllPort: Flow<String> = _listAllPort.map { portList ->
        portList.joinToString(separator = "\n") { port ->
            "[${port.id}] ${port.name} is ${port.status.toString().lowercase()} ${port.beingConnectedWith.lowercase()}"
        }
    }

    // List port drop down
    private val _listDropDownPort = MutableStateFlow<List<String>>(emptyList())
    val listDropDownPortFlow: StateFlow<List<String>> = _listDropDownPort

    private val _selectedPortVendingMachineItem = MutableStateFlow<String>("")
    private val _selectedPortCashBoxItem = MutableStateFlow<String>("")
    private val _selectedBaudRateVendingMachineItem = MutableStateFlow<String>("")
    private val _selectedBaudRateCashBoxItem = MutableStateFlow<String>("")
    private val _selectedProtocolTypeCashBoxItem = MutableStateFlow<String>("")
    private val _selectedProtocolTypeVendingMachineItem = MutableStateFlow<String>("")

    var selectedPortVendingMachineItem = mutableStateOf(listDropDownPortFlow.value.firstOrNull() ?: "")
    var selectedPortCashBoxItem = mutableStateOf(listDropDownPortFlow.value.firstOrNull() ?: "")
    var selectedBaudRateVendingMachineItem = mutableStateOf(listDropDownPortFlow.value.firstOrNull() ?: "")
    var selectedBaudRateCashBoxItem = mutableStateOf(listDropDownPortFlow.value.firstOrNull() ?: "")
    var selectedProtocolTypeVendingMachineItem = mutableStateOf(listDropDownPortFlow.value.firstOrNull() ?: "")
    var selectedProtocolTypeCashBoxItem = mutableStateOf(listDropDownPortFlow.value.firstOrNull() ?: "")

    // Tab selected
    private val _currentSelected = MutableStateFlow("CASH BOX")
    val currentSelected: StateFlow<String> get() = _currentSelected

    // List baud rate drop down
    val listBaudRate = listOf("9600", "19200", "38400", "57600", "115200")

    // List protocol type drop down
    val listProtocolType = listOf(ProtocolType.RS232.toString(), ProtocolType.MDB.toString())

    // Port in storage
    private var _cashBoxPort : CashBoxPort
    private var _vendingMachinePort : VendingMachinePort

    private lateinit var beingConnectedWith : String

    // Selected tab
    fun selectItem(item: String) {
        _currentSelected.value = item
    }

    init {
        getListSerialPort()

        val listTypeCashBox: Type = object : TypeToken<CashBoxPort>() {}.type
        val jsonCashBox = storageDataSource.readJsonFromStorage(storageDataSource.pathFileJsonSetUpCashBoxPort)
        Logger.info("json: $jsonCashBox")
        _cashBoxPort = Gson().fromJson(jsonCashBox, listTypeCashBox)

        val listTypeVendingMachine: Type = object : TypeToken<VendingMachinePort>() {}.type
        val jsonVendingMachine = storageDataSource.readJsonFromStorage(storageDataSource.pathFileJsonSetUpVendingMachinePort)
        Logger.info("json: $jsonVendingMachine")
        _vendingMachinePort = Gson().fromJson(jsonVendingMachine, listTypeVendingMachine)

        selectedPortVendingMachineItem = mutableStateOf(_vendingMachinePort.port)
        selectedPortCashBoxItem = mutableStateOf(_cashBoxPort.port)
        selectedBaudRateVendingMachineItem = mutableStateOf(_vendingMachinePort.baudRate)
        selectedBaudRateCashBoxItem = mutableStateOf(_cashBoxPort.baudRate)
        selectedProtocolTypeCashBoxItem = mutableStateOf(_cashBoxPort.protocolType)
        selectedProtocolTypeVendingMachineItem = mutableStateOf(_vendingMachinePort.protocolType)
    }

    fun updateSelectPortVendingMachineItem(item: String) {
        _selectedPortVendingMachineItem.value = item
    }
    fun updateSelectPortCashBoxItem(item: String) {
        _selectedPortCashBoxItem.value = item
    }
    fun updateSelectBaudRateVendingMachineItem(item: String) {
        _selectedBaudRateVendingMachineItem.value = item
    }
    fun updateSelectBaudRateCashBoxItem(item: String) {
        _selectedBaudRateCashBoxItem.value = item
    }

    fun updateSelectProtocolTypeCashBoxItem(item: String) {
        _selectedProtocolTypeCashBoxItem.value = item
    }

    fun updateSelectProtocolTypeVendingMachineItem(item: String) {
        _selectedProtocolTypeVendingMachineItem.value = item
    }

    fun savePort() {
        viewModelScope.launch {
            setLoadingState(true)
            portConnectionDataSource.closeCashBoxPort()
            portConnectionDataSource.closeVendingMachinePort()
            val cashBoxPort = CashBoxPort(
                protocolType = selectedProtocolTypeCashBoxItem.value,
                port = selectedPortCashBoxItem.value,
                baudRate = selectedBaudRateCashBoxItem.value,
            )
            val vendingMachinePort = VendingMachinePort(
                protocolType = selectedProtocolTypeVendingMachineItem.value,
                port = selectedPortVendingMachineItem.value,
                baudRate = selectedBaudRateVendingMachineItem.value,
            )
            storageDataSource.saveJsonToStorage(
                Gson().toJson(cashBoxPort),
                storageDataSource.pathFileJsonSetUpCashBoxPort
            )
            storageDataSource.saveJsonToStorage(
                Gson().toJson(vendingMachinePort),
                storageDataSource.pathFileJsonSetUpVendingMachinePort
            )
            portConnectionDataSource.openPortCashBox(cashBoxPort.port)
            portConnectionDataSource.openPortVendingMachine(vendingMachinePort.port)
            portConnectionDataSource.startReadingCashBox()
            portConnectionDataSource.startReadingVendingMachine()

            delay(1000)
            setLoadingState(false)
        }
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
        setLoadingState(true)
        val list = portConnectionDataSource.getAllSerialPorts()
        if (list.isEmpty()) return@launch
        processPorts(list)
        setLoadingState(false)
    }

    private suspend fun processPorts(ports: Array<String>) {
        val portList = mutableListOf<Port>()
        val dropDownList = mutableListOf<String>()
        portConnectionDataSource.closeCashBoxPort()
        portConnectionDataSource.closeVendingMachinePort()
        ports.forEachIndexed { index, port ->
            portConnectionDataSource.makeDataEmpty()
            val portStatus = getPortStatus(port)
            val typePort =
            if (port.contains("ttyUSB") ) PortType.USB
            else if (port.contains("ttyS")) PortType.SERIAL
            else PortType.OTHER
            val tmpPort = Port((index+1).toString(), port, typePort, portStatus, beingConnectedWith)
            portList.add(tmpPort)
            if(tmpPort.status == PortStatus.CONNECTED || tmpPort.status == PortStatus.AVAILABLE) {
                dropDownList.add(tmpPort.name)
            }
        }
        _listAllPort.value = portList as ArrayList<Port>
        _listDropDownPort.value = dropDownList
        portConnectionDataSource.openPortCashBox(_cashBoxPort.port)
        portConnectionDataSource.openPortVendingMachine(_vendingMachinePort.port)
        portConnectionDataSource.startReadingCashBox()
        portConnectionDataSource.startReadingVendingMachine()
    }

    private suspend fun getPortStatus(port: String): PortStatus {
        beingConnectedWith = ""
        val statusConnectCashBox = portConnectionDataSource.openPortCashBox(port)

        if (statusConnectCashBox == -1) {
            return PortStatus.UNAVAILABLE
        }
        portConnectionDataSource.startReadingCashBox()
        portConnectionDataSource.sendCommandCashBox(AppByteArrays.cashBoxPollStatus)
        try {
            waitForDataFromCashBox()
            beingConnectedWith = "with cash box"
            portConnectionDataSource.closeCashBoxPort()
            return PortStatus.CONNECTED
        } catch (e: TimeoutCancellationException) {
            // Handle the timeout case if needed
        }

        val statusConnectVendingMachine = portConnectionDataSource.openPortVendingMachine(port)

        if (statusConnectVendingMachine == -1) {
            return PortStatus.UNAVAILABLE
        }
        portConnectionDataSource.startReadingVendingMachine()
        portConnectionDataSource.sendCommandVendingMachine(AppByteArrays.vendingMachineTurnOnLight)
        try {
            waitForDataFromVendingMachine()
            beingConnectedWith = "with vending machine"
            portConnectionDataSource.closeVendingMachinePort()
            return PortStatus.CONNECTED
        } catch (e: TimeoutCancellationException) {
            // Handle the timeout case if needed
        }

        portConnectionDataSource.closeCashBoxPort()
        portConnectionDataSource.closeVendingMachinePort()

        return PortStatus.AVAILABLE
    }

    fun turnOnLight() {
        portConnectionDataSource.sendCommandVendingMachine(AppByteArrays.vendingMachineTurnOnLight)
    }

    fun turnOffLight() {
        portConnectionDataSource.sendCommandVendingMachine(AppByteArrays.vendingMachineTurnOffLight)
    }

    fun resetCashBox() {
        portConnectionDataSource.sendCommandCashBox(AppByteArrays.cashBoxReset)
    }

    fun enableCashBox() {
        portConnectionDataSource.sendCommandCashBox(AppByteArrays.cashBoxEnable123456789)
    }
}