package com.leduytuanvu.vendingmachine.features.vendingMachine.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.core.datasource.SharedPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeVendingMachineViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : ViewModel() {

}