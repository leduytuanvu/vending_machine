package com.leduytuanvu.vendingmachine.features.authentication.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leduytuanvu.vendingmachine.core.common.AppConstants
import com.leduytuanvu.vendingmachine.core.datasource.SharedPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : ViewModel() {

}