package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.repositoryImpl

import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.domain.repository.SetUpSystemSettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class SetUpSystemSettingsRepositoryImpl @Inject constructor() : SetUpSystemSettingsRepository  {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}