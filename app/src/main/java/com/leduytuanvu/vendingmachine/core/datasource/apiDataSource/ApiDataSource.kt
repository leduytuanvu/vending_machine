package com.leduytuanvu.vendingmachine.core.datasource.apiDataSource

import com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.request.LoginRequest
import com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.response.LoginResponse
import com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.domain.contract.product.response.GetAllProductResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import javax.inject.Inject

interface ApiService {
    @GET("product-service/product/list") // Change to your actual endpoint
    suspend fun getAllProduct(): Response<GetAllProductResponse>

    @Headers("Content-Type: application/json", "Accept-Language: en-US")
    @POST("user-service/employee/auth") // Change to your actual endpoint
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}

class ApiDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllProduct(): Response<GetAllProductResponse> {
        return apiService.getAllProduct()
    }

    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return apiService.login(loginRequest)
    }
}
