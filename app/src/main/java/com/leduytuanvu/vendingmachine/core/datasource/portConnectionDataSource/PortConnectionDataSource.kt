package com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource

import android.annotation.SuppressLint
import android.util.Log
import com.leduytuanvu.vendingmachine.core.common.AppByteArrays
import com.leduytuanvu.vendingmachine.core.utils.Logger
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class PortConnectionDataSource {
    // CoroutineScope (Dispatchers.IO + SupervisorJob())
    private val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    // Data from cash box
    private val _dataFromCashBox = MutableStateFlow<String>("")
    val dataFromCashBox: StateFlow<String> = _dataFromCashBox
    // Data from vending machine
    private val _dataFromVendingMachine = MutableStateFlow<String>("")
    val dataFromVendingMachine: StateFlow<String> = _dataFromVendingMachine

    // Data from vending machine
    private val _listSerialPort = MutableStateFlow<Array<String>>(arrayOf())
    val listSerialPort: StateFlow<Array<String>> = _listSerialPort

    // Status of vending machine
    private var fdPortVendingMachine: Int = -1
    // Status of cash box
    private var fdPortCashBox: Int = -1

    // Port connection helper
    private val portConnectionHelperDataSource = PortConnectionHelperDataSource()

    fun getAllSerialPorts(): Array<String> {
        val listSerialPort = portConnectionHelperDataSource.getAllSerialPorts()
        coroutineScope.launch { _listSerialPort.emit(listSerialPort) }
        Logger.info("${javaClass.simpleName}: list size serial ports = ${listSerialPort.size}")
        return listSerialPort
    }

    // Connect vending machine port
    fun connectVendingMachinePort() {
        fdPortVendingMachine = portConnectionHelperDataSource.openPortVendingMachine("/dev/", "ttyS4", 9600)
        Logger.info("${javaClass.simpleName}: fdPortVendingMachine = $fdPortVendingMachine")
    }
    // Connect cash box ports
    fun connectCashBoxPort() {
        fdPortCashBox = portConnectionHelperDataSource.openPortCashBox("/dev/", "ttyS3", 9600)
        Logger.info("${javaClass.simpleName}: fdPortCashBox = $fdPortCashBox")
    }

    fun checkConnectPort(port: String) : Int {
        return portConnectionHelperDataSource.openPortVendingMachine("/dev/", port, 9600)
    }

    // Disconnect vending machine ports
    fun disconnectVendingMachinePort() {
        portConnectionHelperDataSource.closePortVendingMachine()
    }
    // Disconnect cash box ports
    fun disconnectCashBoxPort() {
        portConnectionHelperDataSource.closePortCashBox()
    }

    // Start reading vending machine ports
    fun startReadingVendingMachine() {
        readThreadVendingMachine.start()
    }
    // Start reading cash box ports
    fun startReadingCashBox() {
        readThreadCashBox.start()
    }

    // Read thread vending machine
    private val readThreadVendingMachine = object : Thread() {
        @SuppressLint("SuspiciousIndentation")
        override fun run() {
            Logger.info("PortConnectionDataSource: start read thread vending machine")
            while (!currentThread().isInterrupted) {
                try {
                    portConnectionHelperDataSource.startReadingVendingMachine(512) { data ->
                        val dataHexString = byteArrayToHexString(data)
                        Logger.info("PortConnectionDataSource: data = $dataHexString")
                        coroutineScope.launch {
                            _dataFromVendingMachine.emit(dataHexString)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    break
                }
            }
        }
    }
    // Read thread cash box
    private val readThreadCashBox = object : Thread() {
        @SuppressLint("SuspiciousIndentation")
        override fun run() {
            Logger.info("PortConnectionDataSource: start read thread cash box")
            while (!currentThread().isInterrupted) {
                try {
                    portConnectionHelperDataSource.startReadingCashBox(512) { data ->
                        val dataHexString = byteArrayToHexString(data)
                        Logger.info("PortConnectionDataSource: data = $dataHexString")
                        coroutineScope.launch {
                            _dataFromCashBox.emit(dataHexString)
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    break
                }
            }
        }
    }

    // Send command vending machine
    fun sendCommandVendingMachine(byteArray: ByteArray) : Int {
        return portConnectionHelperDataSource.writeDataPortVendingMachine(byteArray)
    }
    // Send command cash box
    fun sendCommandCashBox(byteArray: ByteArray) : Int {
        return portConnectionHelperDataSource.writeDataPortCashBox(byteArray)
    }

    private fun byteArrayToHexString(byteArray: ByteArray): String {
        return byteArray.joinToString(",") { "%02X".format(it) }
    }
}

