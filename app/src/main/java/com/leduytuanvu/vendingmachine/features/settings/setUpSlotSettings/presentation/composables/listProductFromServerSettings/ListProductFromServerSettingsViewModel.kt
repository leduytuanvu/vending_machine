package com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.presentation.composables.listProductFromServerSettings

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leduytuanvu.vendingmachine.base.VendingMachineViewModel
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.data.model.Slot
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.lang.reflect.Type
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ListProductFromServerSettingsViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    private val storageDataSource: StorageDataSource,
    @ApplicationContext private val context: Context,
) : VendingMachineViewModel() {

    private val _listProductForSale = MutableStateFlow<List<Product>>(emptyList())
    val listProductForSale: StateFlow<List<Product>> = _listProductForSale

    init {
        if (storageDataSource.fileIsExist(storageDataSource.pathFileJsonListOfProductGetFromServer)) {
            val listProductFromServerTmp: Type = object : TypeToken<List<Product>>() {}.type
            val jsonListProductFromServerTmp = storageDataSource.readJsonFromStorage(storageDataSource.pathFileJsonListOfProductGetFromServer)
            _listProductForSale.value = Gson().fromJson(jsonListProductFromServerTmp, listProductFromServerTmp)
        }
    }


}