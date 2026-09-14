package com.example.product_show.domain.di

import com.example.product_show.domain.dispatcher.DispatcherProvider
import com.example.product_show.domain.dispatcher.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {
    @Binds
    @Singleton
    abstract fun provideDispatcherProvider(dispatcherProviderImpl: DispatcherProviderImpl): DispatcherProvider
}