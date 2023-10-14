package com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.leduytuanvu.vendingmachine.base.VendingMachineViewModel
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.authentication.login.domain.contract.login.request.LoginRequest
import com.leduytuanvu.vendingmachine.features.authentication.login.domain.repository.LoginRepository
import com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.domain.repository.GetAllProductRepository
import com.leduytuanvu.vendingmachine.features.vendingMachine.homeVendingMachine.data.models.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class SetUpProductSettingsViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val getAllProductForSaleRepository: GetAllProductRepository,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource,
    private val storageDataSource: StorageDataSource,
    @ApplicationContext private val context: Context,
) : VendingMachineViewModel() {

    val itemsList = listOf("LOAD PRODUCT", "SET UP PRODUCT")

    private val _currentSelected = MutableStateFlow(itemsList[0])
    val currentSelected: StateFlow<String> get() = _currentSelected

    fun selectItem(item: String) {
        _currentSelected.value = item
    }

    fun loadProduct() {
        viewModelScope.launch {
            setLoadingState(true)
            val loginRequest = LoginRequest("huy333", "123")
            val loginResponse = loginRepository.login(loginRequest)
            Logger.info("loginResponse: ${loginResponse}}")
            // Check if response is successful and contains the token
            // Check if the result is a success
            if (loginResponse.isSuccess) {
                // Get the value from the result if it's a success
                val response = loginResponse.getOrNull()
                if (response != null) {
                    val token = response.access_token // Adjust this based on your LoginResponse structure
                    sharedPreferencesDataSource.setString("jwt", token)
                    Logger.info("loginResponse sharedPreferencesDataSource: ${sharedPreferencesDataSource.getString("jwt", "")}}")
                    val getAllProductForSaleResponse = getAllProductForSaleRepository.getProductList()

                    if (getAllProductForSaleResponse.isSuccess) {
                        val responseGetAllProductForSale = getAllProductForSaleResponse.getOrNull()
                        if (responseGetAllProductForSale != null) {
                            Logger.error("Size list: ${responseGetAllProductForSale.size}")
                            saveListOfProductToStorage(responseGetAllProductForSale)
                            setShowToastState(true)
//                            Toast.makeText(context, "Load product success", Toast.LENGTH_SHORT).show()
                        }  else {
                            // Handle the case where the result is a failure
                            val exception = loginResponse.exceptionOrNull()
//                            Toast.makeText(context, "Load product fail!: ${exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // Handle the case where the result is a failure
                        val exception = loginResponse.exceptionOrNull()
//                        Toast.makeText(context, "Load product fail!: ${exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                } else {
//                    Toast.makeText(context, "Not have any product!", Toast.LENGTH_SHORT).show()
                }
            } else {
                // Handle the case where the result is a failure
                val exception = loginResponse.exceptionOrNull()
                Logger.error("Error logging in: ${exception?.message}")
//                Toast.makeText(context, "Load product fail!", Toast.LENGTH_SHORT).show()
            }
            setLoadingState(false)
        }
    }

    private fun saveListOfProductToStorage (listProduct: List<Product>) {
        try {
            storageDataSource.saveJsonToStorage(
                Gson().toJson(listProduct),
                storageDataSource.pathFileJsonListOfProductGetFromServer
            )
        } catch (throwable: Throwable) {
            ErrorHandler.handle(context, throwable)
        }
    }

    suspend fun loadProductFromApi() {
//        val loginRepository = LoginRepositoryImpl()
//        val loginRequest = LoginRequest("huy333", "123")
//        val loginResponse = loginRepository.login(loginRequest)
//        Logger.info("loginResponse: $loginResponse")
//        if (loginResponse.isSuccessful) {
//            // Save JWT on successful login
//            val jwt = loginResponse.body()?.token
//            if (jwt != null) {
//                secureStorage.saveJwt(jwt)
//
//                // Fetch product list after successful login
//                val productsResponse = productRepository.getAllProduct()
//                if (productsResponse.isSuccessful) {
//                    val products = productsResponse.body()?.data // Use your actual data model here
//                    // TODO: Do something with the fetched products
//                } else {
//                    // Handle error in product list response
//                }
//            }
//        } else {
//            // Handle error in login
//        }
    }
}