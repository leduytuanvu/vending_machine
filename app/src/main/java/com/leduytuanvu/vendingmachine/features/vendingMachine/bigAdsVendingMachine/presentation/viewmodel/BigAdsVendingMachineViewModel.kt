package com.leduytuanvu.vendingmachine.features.vendingMachine.bigAdsVendingMachine.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.R
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import com.leduytuanvu.vendingmachine.core.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class BigAdsVendingMachineViewModel @Inject constructor(
    private val storageDataSource: StorageDataSource,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var listAds = ArrayList<String>()

    init {
        try {
            listAds = storageDataSource.getListVideoAdsFromStorage()
            if (listAds.isEmpty()) {
                storageDataSource.saveVideoAdsFromAssetToStorage(context, R.raw.ads1, "ads1.mp4")
                storageDataSource.saveVideoAdsFromAssetToStorage(context, R.raw.ads2, "ads2.mp4")
                storageDataSource.saveVideoAdsFromAssetToStorage(context, R.raw.ads3, "ads3.mp4")
                listAds = storageDataSource.getListVideoAdsFromStorage()
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }
}
