package com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.domain.contract.product.response

import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product

data class GetAllProductResponse(
    val code: Int,
    val message: String,
    val data: List<Product>
)