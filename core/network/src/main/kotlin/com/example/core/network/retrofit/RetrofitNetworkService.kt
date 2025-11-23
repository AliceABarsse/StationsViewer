package com.example.core.network.retrofit

import com.example.core.network.StationsViewerNetworkDataSource
import com.example.core.network.model.NetworkBrand
import com.example.core.network.model.NetworkProgram
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

internal class RetrofitNetworkService (networkJson: Json) : StationsViewerNetworkDataSource{

    private val service = Retrofit.Builder()
        .baseUrl("https://openapi.radiofrance.fr")
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()),)
        .build()
        .create(RadioFranceGraphQLService::class.java)

    override suspend fun getStations(): List<NetworkBrand> {

        val queryObject = JSONObject()
        queryObject.put("query", "{ brands { id title baseline description websiteUrl playerUrl liveStream } }")

        val response = service.postQuery(queryObject.toString())
        val responseBody = response.string()

        println(responseBody)

        return emptyList()
    }

    override suspend fun getPrograms(stationId: String): List<NetworkProgram> {
        TODO("Not yet implemented")
    }
}