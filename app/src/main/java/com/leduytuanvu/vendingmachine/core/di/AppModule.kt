package com.leduytuanvu.vendingmachine.core.di

import android.content.Context
import androidx.navigation.NavController
import com.leduytuanvu.vendingmachine.core.datasource.apiDataSource.ApiDataSource
import com.leduytuanvu.vendingmachine.core.datasource.apiDataSource.ApiService
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.network.RetrofitConfig
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.features.authentication.login.data.repositories.LoginRepositoryImpl
import com.leduytuanvu.vendingmachine.features.authentication.login.domain.repository.LoginRepository
import com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.data.repositoryImpl.GetAllProductRepositoryImpl
import com.leduytuanvu.vendingmachine.features.settings.setUpProductSettings.domain.repository.GetAllProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofitConfig(
        sharedPreferencesDataSource: SharedPreferencesDataSource
    ): RetrofitConfig {
        return RetrofitConfig(sharedPreferencesDataSource)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofitConfig: RetrofitConfig): ApiService {
        return retrofitConfig.apiService
    }


    @Provides
    @Singleton
    fun provideLoginRepository(apiDataSource: ApiDataSource): LoginRepository {
        return LoginRepositoryImpl(apiDataSource)
    }

    @Provides
    @Singleton
    fun provideGetAllProductRepository(apiDataSource: ApiDataSource): GetAllProductRepository {
        return GetAllProductRepositoryImpl(apiDataSource)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideSharedPreferenceDataSource(@ApplicationContext context: Context): SharedPreferencesDataSource {
        return SharedPreferencesDataSource(context)
    }

    @Provides
    @Singleton
    fun provideStorageDataSource(): StorageDataSource {
        return StorageDataSource()
    }

    @Provides
    @Singleton
    fun provideErrorHandler(): ErrorHandler = ErrorHandler

    @Provides
    @Singleton
    fun provideLogger(): Logger = Logger

    @Provides
    @Singleton
    fun providePortConnectionDataSource(): PortConnectionDataSource = PortConnectionDataSource()
}
