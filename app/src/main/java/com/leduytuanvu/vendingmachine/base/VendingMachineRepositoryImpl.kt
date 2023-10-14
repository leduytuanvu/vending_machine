package com.leduytuanvu.vendingmachine.base

import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.domain.repository.SetUpSystemSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class VendingMachineRepositoryImpl @Inject constructor() : VendingMachineRepository {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}