package com.leduytuanvu.vendingmachine.features.authentication.login.domain.repository

import com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.request.LoginRequest
import com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.response.LoginResponse
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse>
}