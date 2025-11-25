package com.example.core.network.di

import android.util.Log
import com.example.core.domain.network.ServiceApi
import com.example.core.network.BuildConfig
import com.example.core.network.retrofit.RadioFranceGraphQLService
import com.example.core.network.retrofit.RetrofitNetworkService
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class LoggingInterceptor : Interceptor {
    override fun  intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val  t1 = System.nanoTime()
        Log.d("OkHttp", String.format("Sending request %s on %s%n%s",
            request.url, chain.connection(), request.headers))

        val  response = chain.proceed(request)

        val  t2 = System.nanoTime()
        Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
            response.request.url, (t2 - t1) / 1000f, response.headers))

        return response
    }
}
val networkModule = module {

    single<ServiceApi> {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val logInterceptor = LoggingInterceptor()
        val authInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("x-token", BuildConfig.API_KEY)
                .build()
            chain.proceed(newRequest)
        }

        val service = Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(logInterceptor)
                    .build()
            )
            .baseUrl("https://openapi.radiofrance.fr")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()),)
            .build()
            .create(RadioFranceGraphQLService::class.java)

        RetrofitNetworkService(service = service)
    }
}