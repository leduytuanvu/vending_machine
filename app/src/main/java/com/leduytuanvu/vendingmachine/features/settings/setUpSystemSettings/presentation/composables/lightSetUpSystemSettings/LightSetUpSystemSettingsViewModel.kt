package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.lightSetUpSystemSettings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.repositoryImpl.SetUpSystemSettingsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LightSetUpSystemSettingsViewModel @Inject constructor(
    private val portConnectionDataSource: PortConnectionDataSource,
    val setUpSystemSettingsRepositoryImpl: SetUpSystemSettingsRepositoryImpl,
) : ViewModel() {

    private val _listAllPort = MutableStateFlow<String>("")
    val listAllPort: StateFlow<String> = _listAllPort

    init {
        getListSerialPort()
    }

    fun getListSerialPort() {
        viewModelScope.launch {
            setUpSystemSettingsRepositoryImpl.setLoading(true)

            setUpSystemSettingsRepositoryImpl.setLoading(false)
        }
    }

    fun turnOnLight() {
        viewModelScope.launch {

        }
    }
}