package com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.data.model

data class Slot(
    var slotOnDevice: Int = 0,
    val brief: String,
    val image_local_url: String,
    val code: String,
    val price: Int,
    var inventory: Int = 0,
    var capacity: Int = 0,
    val image_url: String,
    val spings_type: String,
    val id: String,
    val category: Int,
    val product_name: String,
    var status: Boolean = true,
)