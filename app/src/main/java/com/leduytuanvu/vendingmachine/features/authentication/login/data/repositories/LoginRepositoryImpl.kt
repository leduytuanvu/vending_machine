package com.leduytuanvu.vendingmachine.features.authentication.login.data.repositories

import com.leduytuanvu.vendingmachine.core.datasource.apiDataSource.ApiDataSource
import com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.request.LoginRequest
import com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.response.LoginResponse
import com.leduytuanvu.vendingmachine.features.authentication.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource
) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = apiDataSource.login(loginRequest)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                // handle API error response
                Result.failure(RuntimeException("Error fetching product list: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(RuntimeException("Error fetching product list", e))
        }
    }

}