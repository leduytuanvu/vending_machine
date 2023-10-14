package com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.response

import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product

data class LoginResponse(
    val access_token: String,
    val token_type: String,
    val access_token_expires: String,
    val time_current: Double,
    val time_token_expires: Double,
)