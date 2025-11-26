package com.example.core.network.retrofit

import com.example.core.domain.network.ServiceApi
import com.example.core.model.data.PaginatedProgramList
import com.example.core.model.data.Station
import com.example.core.network.mapping.toProgram
import com.example.core.network.mapping.toStation
import com.example.core.network.model.getBrandsQuery
import com.example.core.network.model.getProgramsQuery
import com.example.core.network.model.items
import com.example.core.network.model.lastCursor

internal class RetrofitNetworkService(private val service: RadioFranceGraphQLService) : ServiceApi {

    override suspend fun getStations(): List<Station> {

        val requestBody = getBrandsQuery()
        val response = service.postBrandsQuery(requestBody)

        if (response.errors != null) {
            // TODO use a more specific Result<List<Station>, BrandException> return value
            throw Exception("GraphQL Error: ${response.errors.firstOrNull()?.message}")
        }

        val networkBrands = response.data?.brands ?: emptyList()

        return networkBrands.flatMap { brand ->
            brand.toStation()
        }
    }

    override suspend fun getPrograms(
        stationId: String,
        pageSize: Int,
        lastCursor: String?,
    ): PaginatedProgramList {
        val requestBody = getProgramsQuery(stationId = stationId, pageSize = pageSize, lastCursor = lastCursor)
        val response = service.postShowsQuery(requestBody)

        if (response.errors != null) {
            // TODO use a more specific Result<List<Station>, BrandException> return value
            throw Exception("GraphQL Error: ${response.errors.firstOrNull()?.message}")
        }

        val networkShows = response.items
        val lastCursor = response.lastCursor

        return PaginatedProgramList(
            list = networkShows.map { networkProgram ->
                networkProgram.toProgram()
            },
            pageSize = pageSize,
            lastCursor = lastCursor,
        )
    }
}