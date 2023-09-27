package com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.information

import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.core.datasource.StorageDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InformationVendingMachineViewModel @Inject constructor(
    private val storageDataSource: StorageDataSource,
) : ViewModel() {

}