package com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.presentation.composables.cashBoxTypeSetUpSystemSettings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leduytuanvu.vendingmachine.base.VendingMachineViewModel
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.enums.CashBoxTypeEnum
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.cashBox.CashBoxType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class CashBoxTypeSetUpSystemSettingsViewModel @Inject constructor(
    private val portConnectionDataSource: PortConnectionDataSource,
    private val storageDataSource: StorageDataSource,
) : VendingMachineViewModel() {
    val listTypeCashBox = listOf(CashBoxTypeEnum.ICT.toString(), CashBoxTypeEnum.ANOTHER.toString())
    private val _selectedCashBoxTypeItem = MutableStateFlow<String>("")
    var selectedCashBoxTypeItem = mutableStateOf(listTypeCashBox.firstOrNull() ?: "")

    var cashBoxType : CashBoxType

    init {
        val checkFileSetUpCashBoxType =
            storageDataSource.fileIsExist(storageDataSource.pathFileJsonSetUpCashBoxType)
        if (!checkFileSetUpCashBoxType) {
            cashBoxType = CashBoxType(
                type = CashBoxTypeEnum.ICT.toString(),
            )
            storageDataSource.saveJsonToStorage(
                Gson().toJson(cashBoxType),
                storageDataSource.pathFileJsonSetUpCashBoxType
            )
        } else {
            val listType: Type = object : TypeToken<CashBoxType>() {}.type
            val json = storageDataSource.readJsonFromStorage(storageDataSource.pathFileJsonSetUpCashBoxType)
            Logger.info("json: $json")
            cashBoxType = Gson().fromJson(json, listType)
        }

        selectedCashBoxTypeItem = mutableStateOf( cashBoxType.type)
    }

    fun updateSelectCashBoxTypeItem(item: String) {
        _selectedCashBoxTypeItem.value = item
    }

    fun save() {
        viewModelScope.launch {
            setLoadingState(true)
            val cashBoxType = CashBoxType(
                type = selectedCashBoxTypeItem.value,
            )
            storageDataSource.saveJsonToStorage(
                Gson().toJson(cashBoxType),
                storageDataSource.pathFileJsonSetUpCashBoxType
            )
            delay(1000)
            setLoadingState(false)
        }
    }
}