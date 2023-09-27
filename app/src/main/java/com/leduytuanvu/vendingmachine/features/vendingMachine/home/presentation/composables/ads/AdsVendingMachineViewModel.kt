package com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.ads

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.R
import com.leduytuanvu.vendingmachine.core.datasource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import com.leduytuanvu.vendingmachine.core.utils.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class AdsVendingMachineViewModel @Inject constructor(
    private val storageDataSource: StorageDataSource,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var listAds = ArrayList<String>()

//    Board android
//    init {
//        try {
//            listAds = storageDataSource.getListVideoAds()
//            if (listAds.isEmpty()) {
//                storageDataSource.saveVideoAds(context, R.raw.ads1, "ads1.mp4")
//                storageDataSource.saveVideoAds(context, R.raw.ads2, "ads2.mp4")
//                storageDataSource.saveVideoAds(context, R.raw.ads3, "ads3.mp4")
//                listAds = storageDataSource.getListVideoAds()
//            }
//        } catch (throwable: Throwable) {
//            ErrorHandler.handle(context, throwable)
//        }
//    }

    // Phone
    init {
        try {
            listAds = storageDataSource.getListVideoAdsPhone()
            if (listAds.isEmpty()) {
                Logger.info("listAds.isEmpty()")
                storageDataSource.saveVideoAdsPhone(context, R.raw.ads1, "ads1.mp4")
                storageDataSource.saveVideoAdsPhone(context, R.raw.ads2, "ads2.mp4")
                storageDataSource.saveVideoAdsPhone(context, R.raw.ads3, "ads3.mp4")
                listAds = storageDataSource.getListVideoAdsPhone()
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    fun getListVideoAds(): ArrayList<String> {
        try {
            listAds = storageDataSource.getListVideoAdsPhone()
            if (listAds.isEmpty()) {
                Logger.info("listAds.isEmpty()")
                storageDataSource.saveVideoAdsPhone(context, R.raw.ads1, "ads1.mp4")
                storageDataSource.saveVideoAdsPhone(context, R.raw.ads2, "ads2.mp4")
                storageDataSource.saveVideoAdsPhone(context, R.raw.ads3, "ads3.mp4")
                listAds = storageDataSource.getListVideoAdsPhone()
            }
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
        return listAds
    }

    fun turnOffAds() {
        try {
            Logger.info("Turn off ads clicked")
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }
}
