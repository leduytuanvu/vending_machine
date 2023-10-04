package com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource

import com.leduytuanvu.vendingmachine.core.utils.Logger

class PortConnectionHelperDataSource {
    init {
        // Load the JNI library
        System.loadLibrary("NativePortCommunication")
        Logger.info("${javaClass.simpleName}: loading native port communication library successfully")
    }

    // Open, close, read, write vending machine port
    external fun openPortVendingMachine(path: String, portName: String, baudRate: Int): Int
    external fun closePortVendingMachine()
    external fun readDataPortVendingMachine(bufferSize: Int, callback: DataReceivedCallbackVendingMachine)
    external fun writeDataPortVendingMachine(data: ByteArray): Int
    // Open, close, read, write vending machine port
    external fun openPortCashBox(path: String, portName: String, baudRate: Int): Int
    external fun closePortCashBox()
    external fun readDataPortCashBox(bufferSize: Int, callback: DataReceivedCallbackCashBox)
    external fun writeDataPortCashBox(data: ByteArray): Int

    external fun getAllSerialPorts(): Array<String>

    // Start reading vending machine port
    fun startReadingVendingMachine(bufferSize: Int, callback: (ByteArray) -> Unit) {
        val readDataCallbackVendingMachine = object : DataReceivedCallbackVendingMachine {
            override fun onDataReceivedVendingMachine(data: ByteArray) {
                if (data.size >= 5) callback(data)
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    Logger.error("${javaClass.simpleName}: ${e.message}")
                }
            }
        }
        readDataPortVendingMachine(bufferSize, readDataCallbackVendingMachine)
    }
    // Start reading cash box port
    fun startReadingCashBox(bufferSize: Int, callback: (ByteArray) -> Unit) {
        val readDataCallbackCashBox = object : DataReceivedCallbackCashBox {
            override fun onDataReceivedCashBox(data: ByteArray) {
                if (data.size >= 5) callback(data)
                try {
                    Thread.sleep(50)
                } catch (e: InterruptedException) {
                    Logger.error("${javaClass.simpleName}: ${e.message}")
                }
            }
        }
        readDataPortCashBox(bufferSize, readDataCallbackCashBox)
    }
}