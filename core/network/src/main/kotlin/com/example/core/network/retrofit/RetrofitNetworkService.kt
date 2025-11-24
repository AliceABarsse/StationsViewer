package com.example.core.network.retrofit

import com.example.core.domain.network.ServiceApi
import com.example.core.model.data.Station
import com.example.core.model.data.Program
import org.json.JSONObject
import retrofit2.Retrofit

internal class RetrofitNetworkService (private val service: RadioFranceGraphQLService) : ServiceApi {



    override suspend fun getStations(): List<Station> {

        val queryObject = JSONObject()
        queryObject.put("query", "{ brands { id title baseline description websiteUrl playerUrl liveStream } }")

        val response = service.postQuery(queryObject.toString())
        val responseBody = response.string()

        println(responseBody)

        // TODO convert network brand to domain Station
        return emptyList()
    }

    override suspend fun getPrograms(stationId: String): List<Program>{
        TODO("Not yet implemented")
    }
}