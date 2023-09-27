package com.leduytuanvu.vendingmachine.core.common

enum class AppLanguageEnums(val id: Int, val value: String) {
    EN(1, "en"),
    VI(2, "vn");

    fun getInfo() = "ID: $id, DESCRIPTION: $value"
}

enum class AppThemeEnums(val id: Int, val value: String) {
    LIGHT(1, "LIGHT"),
    DARK(2, "DARK");

    fun getInfo() = "ID: $id, DESCRIPTION: $value"
}