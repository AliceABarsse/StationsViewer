package com.example.stationsviewer

import android.app.Application
import com.example.core.data.di.dataModule
import com.example.core.domain.di.domainModule
import com.example.core.network.di.networkModule
import com.example.features.di.featuresModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(networkModule, dataModule, domainModule, featuresModule)
        }
    }
}