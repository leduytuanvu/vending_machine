package com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource

interface DataReceivedCallbackVendingMachine {
    fun onDataReceivedVendingMachine(data: ByteArray)
}