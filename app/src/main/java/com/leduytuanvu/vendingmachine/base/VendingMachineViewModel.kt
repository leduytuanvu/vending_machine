package com.leduytuanvu.vendingmachine.base

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leduytuanvu.vendingmachine.features.splash.data.model.toastEvent.ToastEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class VendingMachineViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun setLoadingState(state: Boolean) {
        _isLoading.value = state
    }

    private val _showToast = MutableStateFlow<Boolean>(false)
    val showToast: StateFlow<Boolean> get() = _showToast

    fun setShowToastState(state: Boolean) {
        _showToast.value = state
    }
}