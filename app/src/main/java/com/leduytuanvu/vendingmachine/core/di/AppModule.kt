package com.leduytuanvu.vendingmachine.core.di

import android.content.Context
import com.leduytuanvu.vendingmachine.core.datasource.portConnectionDataSource.PortConnectionDataSource
import com.leduytuanvu.vendingmachine.core.datasource.sharedPreferencesDataSource.SharedPreferencesDataSource
import com.leduytuanvu.vendingmachine.core.datasource.storageDataSource.StorageDataSource
import com.leduytuanvu.vendingmachine.core.utils.ErrorHandler
import com.leduytuanvu.vendingmachine.core.utils.Logger
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

    @Provides
    @Singleton
    fun providePortConnectionDataSource(): PortConnectionDataSource = PortConnectionDataSource()
}
