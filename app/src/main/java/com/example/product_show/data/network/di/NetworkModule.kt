package com.example.product_show.data.network.di

import com.example.product_show.BuildConfig
import com.example.product_show.data.network.api.ProductShowApi
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Module
    @InstallIn(SingletonComponent::class)
    object NetworkProvider {

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .also {
                    if (BuildConfig.DEBUG) {
                        it.addInterceptor(
                            HttpLoggingInterceptor().apply {
                                level = HttpLoggingInterceptor.Level.BODY
                            }
                        )
                    }
                }
                .retryOnConnectionFailure(true).connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build()

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setStrictness(Strictness.LENIENT).create()
                    )
                )
                .addConverterFactory(GsonConverterFactory.create()).build()
        }

        @Provides
        @Singleton
        fun provideExerciseApi(retrofit: Retrofit): ProductShowApi {
            return retrofit.create(ProductShowApi::class.java)
        }
    }
}