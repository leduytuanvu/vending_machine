package com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leduytuanvu.vendingmachine.base.VendingMachineViewModel
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.data.model.Slot
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.reflect.Type
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class SetUpSlotSettingsViewModel @Inject constructor(
    private val storageDataSource: StorageDataSource,
    @ApplicationContext private val context: Context,
) : VendingMachineViewModel() {

    private val _listProductForSale = MutableStateFlow<List<Slot>>(emptyList())
    val listProductForSale: StateFlow<List<Slot>> = _listProductForSale

    private val _isListProductFromServerVisible = MutableStateFlow(false)  // default to true if you want it to be visible initially
    val isListProductFromServerVisible: StateFlow<Boolean> = _isListProductFromServerVisible

    init {
        getListProductForSaleFromStorage ()
    }

    fun toggleListProductVisibility(show: Boolean) {
        _isListProductFromServerVisible.value = show
    }

    private fun getListProductForSaleFromStorage () {
        try {
            val listSlotType: Type = object : TypeToken<List<Slot>>() {}.type
            val jsonListSlot = storageDataSource.readJsonFromStorage(storageDataSource.pathFileJsonListOfProductForSale)
            _listProductForSale.value = Gson().fromJson(jsonListSlot, listSlotType)
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    fun addProductForSale(slot: Slot) {
        toggleListProductVisibility(true)
    }
}