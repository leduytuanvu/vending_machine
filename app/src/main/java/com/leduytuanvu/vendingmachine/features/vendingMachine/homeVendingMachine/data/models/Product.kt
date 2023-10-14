package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models

data class Product(
    val brief: String,
    val image_local_url: String,
    val code: String,
    val price: Int,
    val image_url: String,
    val spings_type: String,
    val id: String,
    val category: Int,
    val product_name: String
)