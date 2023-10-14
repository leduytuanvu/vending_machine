package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.base.VendingMachineViewModel
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.repositoryImpl.SetUpSystemSettingsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class SetUpSystemSettingsViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    @ApplicationContext private val context: Context,
) : VendingMachineViewModel() {

    val itemsList = listOf("VENDING MACHINE TYPE", "CASH BOX TYPE", "PORT", "LIGHT", "SENSOR", "INCHING", "HEATING", "TEMPERATURE")

    private val _currentSelected = MutableStateFlow(itemsList[0])
    val currentSelected: StateFlow<String> get() = _currentSelected

    fun selectItem(item: String) {
        _currentSelected.value = item
    }
}