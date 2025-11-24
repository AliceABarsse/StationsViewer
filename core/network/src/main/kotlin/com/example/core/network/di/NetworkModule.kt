package com.example.core.network.di

import com.example.core.domain.network.ServiceApi
import com.example.core.network.BuildConfig
import com.example.core.network.retrofit.RadioFranceGraphQLService
import com.example.core.network.retrofit.RetrofitNetworkService
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {

    single<ServiceApi> {

        println ("API KEY is ${BuildConfig.API_KEY}")
        val json = Json {
            ignoreUnknownKeys = true
        }
        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                // Use the BuildConfig value you set up earlier
                .header("x-token", BuildConfig.API_KEY)
                .build()
            chain.proceed(newRequest)
        }

        val service = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor).build()
            )
            .baseUrl("https://openapi.radiofrance.fr")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()),)
            .build()
            .create(RadioFranceGraphQLService::class.java)

        RetrofitNetworkService(service = service)
    }
}