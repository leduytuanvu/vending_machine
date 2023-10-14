package com.leduytuanvu.vendingmachine.core.network

import android.annotation.SuppressLint
import com.leduytuanvu.vendingmachine.core.datasource.apiDataSource.ApiService
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitConfig @Inject constructor(
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) {
    companion object {
        private const val BASE_URL = "https://dev-api.avf.vn/"
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val jwt = sharedPreferencesDataSource.getString("jwt", "")
                val newRequestBuilder = originalRequest.newBuilder()

                if (jwt.isNotEmpty()) {
                    newRequestBuilder.addHeader("Authorization", "Bearer $jwt")
                }

                val newRequest = newRequestBuilder.build()
                chain.proceed(newRequest)
            }
            .build()
    }

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}

