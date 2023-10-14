package com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.data.repositoryImpl

import com.leduytuanvu.vendingmachine.core.datasource.apiDataSource.ApiDataSource
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.domain.repository.GetAllProductRepository
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.domain.repository.SetUpSystemSettingsRepository
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class GetAllProductRepositoryImpl @Inject constructor(
    private val apiDataSource: ApiDataSource
) : GetAllProductRepository {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }

    override suspend fun getProductList(): Result<List<Product>> {
        return try {
            val response = apiDataSource.getAllProduct()
            if (response.isSuccessful) {
                Result.success(response.body()?.data ?: emptyList())
            } else {
                // handle API error response
                Result.failure(RuntimeException("Error fetching product list: ${response.message()}"))
            }
        } catch (e: Exception) {
            // handle network/other errors
            Result.failure(RuntimeException("Error fetching product list", e))
        }
    }
}