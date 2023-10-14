package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.vendingMachine

data class VendingMachinePort(
    val protocolType : String,
    val port: String,
    val baudRate: String,
)