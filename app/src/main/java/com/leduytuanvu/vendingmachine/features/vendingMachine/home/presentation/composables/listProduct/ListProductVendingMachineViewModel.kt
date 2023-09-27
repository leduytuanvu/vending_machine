package com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.composables.listProduct

import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.core.datasource.StorageDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListProductVendingMachineViewModel @Inject constructor(
    private val storageDataSource: StorageDataSource,
) : ViewModel() {

}