package com.example.core.network.retrofit

import com.example.core.domain.network.ServiceApi
import com.example.core.model.data.Station
import com.example.core.model.data.Program
import com.example.core.network.mapping.toStation
import com.example.core.network.model.getBrandsQuery

internal class RetrofitNetworkService (private val service: RadioFranceGraphQLService) : ServiceApi {

    override suspend fun getStations(): List<Station> {

        val requestBody = getBrandsQuery()
        val response = service.postQuery(requestBody)

        if (response.errors != null) {
            // TODO use a more specific Result<List<Station>, BrandException> return value
            throw Exception("GraphQL Error: ${response.errors.firstOrNull()?.message}")
        }

        val networkBrands = response.data?.brands ?: emptyList()

        return networkBrands.map { brand ->
            brand.toStation()
        }
    }

    override suspend fun getPrograms(stationId: String): List<Program>{
        TODO("Not yet implemented")
    }
}