package com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.domain.repository

import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product

interface GetAllProductRepository {
    suspend fun getProductList(): Result<List<Product>>
}