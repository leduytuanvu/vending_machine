package com.leduytuanvu.vendingmachine.core.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.leduytuanvu.vendingmachine.core.common.AppConstants
import com.leduytuanvu.vendingmachine.core.datasource.ApiDataSource
import com.leduytuanvu.vendingmachine.core.datasource.ApiService
import com.leduytuanvu.vendingmachine.core.datasource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.datasource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.network.RetrofitConfig
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import com.leduytuanvu.vendingmachine.core.utils.LanguageUtils
import com.leduytuanvu.vendingmachine.core.utils.Logger
import com.leduytuanvu.vendingmachine.core.utils.ThemeUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
}
