package com.leduytuanvu.vendingmachine.features.splash.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.leduytuanvu.vendingmachine.R
import com.leduytuanvu.vendingmachine.base.VendingMachineViewModel
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.settings.setUpSlotSettings.data.model.Slot
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.enums.MachineType
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.enums.ProtocolType
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.cashBox.CashBoxPort
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.vendingMachine.VendingMachinePort
import com.leduytuanvu.vendingmachine.features.settings.setUpSystemSettings.data.model.vendingMachine.VendingMachineType
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Type
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class SplashViewModel @Inject constructor(
    private val portConnectionDataSource: PortConnectionDataSource,
    private val storageDataSource: StorageDataSource,
    @ApplicationContext private val context: Context,
) : VendingMachineViewModel() {

    private val _navigateTo = MutableStateFlow<String?>(null)
    val navigateTo: StateFlow<String?> = _navigateTo

    private lateinit var _cashBoxPort: CashBoxPort
    private lateinit var _vendingMachinePort: VendingMachinePort

    init {
        loadInitData()
    }

    private fun loadInitData() {
        viewModelScope.launch {
            checkFolderAdsExistOrNot()
            checkFileJsonSetUpMachineTypeExistOrNot()
            checkFileJsonSetUpCashBoxPortExistOrNot()
            checkFileJsonSetUpVendingMachinePortExistOrNot()
            checkFileJsonListProductForSaleExistOrNot()
            _navigateTo.value = "homeVendingMachine"
        }
    }

    // Check path folder ads exist or not
    private fun checkFolderAdsExistOrNot () {
        try {
            if (!storageDataSource.folderIsExist(storageDataSource.pathFileAds)) {
                Logger.info("checkFolderAdsExistOrNot is not exist")
                storageDataSource.saveVideoAdsFromAssetToStorage(context, R.raw.ads1, "ads1.mp4")
                storageDataSource.saveVideoAdsFromAssetToStorage(context, R.raw.ads2, "ads2.mp4")
                storageDataSource.saveVideoAdsFromAssetToStorage(context, R.raw.ads3, "ads3.mp4")
            } else {
                Logger.info("checkFolderAdsExistOrNot is exist")
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    // Check path file json set up vending machine exist or not
    private fun checkFileJsonSetUpMachineTypeExistOrNot () {
        try {
            if (!storageDataSource.fileIsExist(storageDataSource.pathFileJsonSetUpMachineType)) {
                Logger.info("checkFileJsonSetUpVendingMachineType is not exist")
                val vendingMachineType = VendingMachineType(
                    type = MachineType.TCN.toString(),
                )
                storageDataSource.saveJsonToStorage(
                    Gson().toJson(vendingMachineType),
                    storageDataSource.pathFileJsonSetUpMachineType
                )
            } else {
                Logger.info("checkFileJsonSetUpVendingMachineType is exist")
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    // Check path file json set up cash box port exist or not
    private fun checkFileJsonSetUpCashBoxPortExistOrNot () {
        try {
            if (!storageDataSource.fileIsExist(storageDataSource.pathFileJsonSetUpCashBoxPort)) {
                Logger.info("checkFileJsonSetUpCashBoxPort is not exist")
                _cashBoxPort = CashBoxPort(
                    protocolType = ProtocolType.RS232.toString(),
                    port = "ttyS1",
                    baudRate = "9600",
                )
                storageDataSource.saveJsonToStorage(
                    Gson().toJson(_cashBoxPort),
                    storageDataSource.pathFileJsonSetUpCashBoxPort
                )
            } else {
                val listType: Type = object : TypeToken<CashBoxPort>() {}.type
                val json = storageDataSource.readJsonFromStorage(storageDataSource.pathFileJsonSetUpCashBoxPort)
                Logger.info("json: $json")
                _cashBoxPort = Gson().fromJson(json, listType)
            }
            if (portConnectionDataSource.openPortCashBox(_cashBoxPort.port) == -1) {
                Logger.info("openPortCashBox is error")
            } else {
                Logger.info("openPortCashBox is success")
                portConnectionDataSource.startReadingCashBox()
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    // Check path file json set up vending machine port exist or not
    private fun checkFileJsonSetUpVendingMachinePortExistOrNot () {
        try {
            if (!storageDataSource.fileIsExist(storageDataSource.pathFileJsonSetUpVendingMachinePort)) {
                Logger.info("checkFileJsonSetUpVendingMachinePort is not exist")
                _vendingMachinePort = VendingMachinePort(
                    protocolType = ProtocolType.RS232.toString(),
                    port = "ttyS2",
                    baudRate = "9600",
                )
                storageDataSource.saveJsonToStorage(
                    Gson().toJson(_vendingMachinePort),
                    storageDataSource.pathFileJsonSetUpVendingMachinePort
                )
            } else {
                val listType: Type = object : TypeToken<VendingMachinePort>() {}.type
                val json = storageDataSource.readJsonFromStorage(storageDataSource.pathFileJsonSetUpVendingMachinePort)
                Logger.info("json: $json")
                _vendingMachinePort = Gson().fromJson(json, listType)
            }
            if (portConnectionDataSource.openPortVendingMachine(_vendingMachinePort.port) == -1) {
                Logger.info("openPortVendingMachine is error")
            } else {
                Logger.info("openPortVendingMachine is success")
                portConnectionDataSource.startReadingVendingMachine()
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    // Check path file json list product for sale exist or not
    private fun checkFileJsonListProductForSaleExistOrNot () {
        try {
            val listProductForSaleTmp: MutableList<Slot> = mutableListOf()
            if (!storageDataSource.fileIsExist(storageDataSource.pathFileJsonListOfProductForSale)) {
                Logger.info("checkFileJsonListProductForSaleExistOrNot is not exist")
                for(i in 1..50) {
                    listProductForSaleTmp.add(
                        Slot(
                            i,
                            "",
                            "",
                            "",
                            10000,
                            0,
                            10,
                            "",
                            "",
                            "",
                            0,
                            "",
                            true
                        )
                    )
                }
                storageDataSource.saveJsonToStorage(
                    Gson().toJson(listProductForSaleTmp),
                    storageDataSource.pathFileJsonListOfProductForSale
                )
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    fun resetNavigationEvent() {
        _navigateTo.value = null
    }
}