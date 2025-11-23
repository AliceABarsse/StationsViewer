package com.example.core.network.di

import com.example.core.network.StationsViewerNetworkDataSource
import com.example.core.network.retrofit.RetrofitNetworkService
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<StationsViewerNetworkDataSource> { RetrofitNetworkService(Json) }
    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }
}