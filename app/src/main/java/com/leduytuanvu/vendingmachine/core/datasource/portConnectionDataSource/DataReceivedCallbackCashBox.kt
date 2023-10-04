package com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource

interface DataReceivedCallbackCashBox {
    fun onDataReceivedCashBox(data: ByteArray)
}