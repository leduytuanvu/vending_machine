package com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.presentation.composables.informationVendingMachine

import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InformationVendingMachineViewModel @Inject constructor(
    private val storageDataSource: StorageDataSource,
) : ViewModel() {

}