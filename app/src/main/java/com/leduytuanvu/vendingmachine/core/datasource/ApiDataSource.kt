package com.leduytuanvu.vendingmachine.core.datasource

import retrofit2.Response

// Define the API calls you'll make.
interface ApiService {
    suspend fun getProductList(): Response<List<String>>
}

class ApiDataSource(private val apiService: ApiService) {
    suspend fun getProductList(): Response<List<String>> {
        return apiService.getProductList()
    }
}
