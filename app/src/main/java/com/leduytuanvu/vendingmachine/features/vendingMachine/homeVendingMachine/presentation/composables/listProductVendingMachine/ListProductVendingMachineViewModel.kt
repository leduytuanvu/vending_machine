package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.listProductVendingMachine

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.data.model.Slot
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class ListProductVendingMachineViewModel @Inject constructor(
    private val storageDataSource: StorageDataSource,
) : ViewModel() {
    // List product
    private val _listOfProductForSale = MutableStateFlow<List<Slot>>(emptyList())
    val listOfProductForSale: StateFlow<List<Slot>> = _listOfProductForSale

    init {
        val listTypeListOfProductForSale: Type = object : TypeToken<List<Slot>>() {}.type
        val jsonListOfProductForSale = storageDataSource.readJsonFromStorage(storageDataSource.pathFileJsonListOfProductForSale)
        Logger.info("json: $jsonListOfProductForSale")
        _listOfProductForSale.value = Gson().fromJson(jsonListOfProductForSale, listTypeListOfProductForSale)
    }

}