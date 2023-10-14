package com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.request

data class LoginRequest(
    val username: String,
    val password: String,
)