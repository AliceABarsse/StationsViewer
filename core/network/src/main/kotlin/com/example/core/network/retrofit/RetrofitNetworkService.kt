package com.example.core.network.retrofit

import com.example.core.domain.network.ServiceApi
import com.example.core.model.data.Station
import com.example.core.model.data.Program
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.json.JSONObject

internal class RetrofitNetworkService (private val service: RadioFranceGraphQLService) : ServiceApi {



    override suspend fun getStations(): List<Station> {

        val queryObject = JSONObject()
        queryObject.put("query", "{ brands { id title baseline description websiteUrl playerUrl liveStream } }")

        val query = """
{
  brands {
    id
    title
    baseline
    description
    websiteUrl
    playerUrl
    liveStream
    localRadios {
      id
      title
      description
      liveStream
      playerUrl
    }
    webRadios {
      id
      title
      description
      liveStream
      playerUrl
    }
  }
}
""".trimIndent()

        val requestBody = buildJsonObject {
            put("query", query)
        }

        val response = service.postQuery(requestBody)
        val responseBody = response.string()

        println(responseBody)

        // TODO convert network brand to domain Station
        return emptyList()
    }

    override suspend fun getPrograms(stationId: String): List<Program>{
        TODO("Not yet implemented")
    }
}