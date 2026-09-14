package com.example.product_show

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProductShowApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}