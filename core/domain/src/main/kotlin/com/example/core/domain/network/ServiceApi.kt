package com.example.core.domain.network

import com.example.core.model.data.PaginatedProgramList
import com.example.core.model.data.Station


interface ServiceApi {

    suspend fun getStations(): List<Station>

    suspend fun getPrograms(
        stationId: String,
        pageSize: Int = 10,
        lastCursor: String? = null,
    ): PaginatedProgramList
}