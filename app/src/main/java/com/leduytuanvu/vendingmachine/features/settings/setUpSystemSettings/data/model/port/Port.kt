package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.port

import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.enums.PortStatus
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.enums.PortType

data class Port(
    val id: String,
    val name: String,
    val type: PortType,
    val status: PortStatus,
    val beingConnectedWith: String
)


