package com.example.product_show.data.di

import com.example.product_show.data.ProductShowRepoImpl
import com.example.product_show.domain.repo.ProductShowRepo
import com.example.product_show.data.network.repo.ProductShowNetworkDataSource
import com.example.product_show.data.network.repo.ProductShowNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun provideProductShowRepo(productShowRepoImpl: ProductShowRepoImpl): ProductShowRepo

    @Binds
    @Singleton
    abstract fun provideProductShowNetworkDataSource(productShowNetworkDataSourceImpl: ProductShowNetworkDataSourceImpl): ProductShowNetworkDataSource
}