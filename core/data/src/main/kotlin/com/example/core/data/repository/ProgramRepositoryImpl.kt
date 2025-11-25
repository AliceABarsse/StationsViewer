package com.example.core.data.repository

import com.example.core.domain.network.ServiceApi
import com.example.core.domain.repository.ProgramRepository
import com.example.core.model.data.PaginatedProgramList

class ProgramRepositoryImpl(
    private val networkDataSource: ServiceApi,
) : ProgramRepository {

    override suspend fun getPrograms(
        stationId: String,
        pageSize: Int,
        lastCursor: String?,
    ): PaginatedProgramList =
        networkDataSource.getPrograms(
            stationId = stationId,
            pageSize = pageSize,
            lastCursor = lastCursor
        )
}
