package com.leduytuanvu.vendingmachine.features.authentication.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : ViewModel() {

}