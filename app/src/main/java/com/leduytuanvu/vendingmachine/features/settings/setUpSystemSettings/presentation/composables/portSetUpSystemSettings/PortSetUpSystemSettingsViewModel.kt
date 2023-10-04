package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.portSetUpSystemSettings

import androidx.compose.runtime.mutableStateOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leduytuanvu.vendingmachine.core.common.AppByteArrays
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.repositoryImpl.SetUpSystemSettingsRepositoryImpl
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.viewmodel.SetUpSystemSettingsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class PortSetUpSystemSettingsViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
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
            val list = portConnectionDataSource.getAllSerialPorts()
            if (list.isEmpty()) {
                _listAllPort.value = "NOT HAVE ANY PORT"
            } else {
                _listAllPort.value = ""
                for (i in list.indices) {
                    _listAllPort.value += "[${i+1}] " + list[i] + "\n"
                    Logger.info("Status: ${portConnectionDataSource.checkConnectPort(list[i])}")
                }
            }
            delay(1000)
            setUpSystemSettingsRepositoryImpl.setLoading(false)
        }
    }
}