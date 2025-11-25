package com.example.core.domain.repository

import com.example.core.model.data.PaginatedProgramList

interface ProgramRepository {
    suspend fun getPrograms(stationId: String, pageSize: Int = 10, lastCursor: String? = null): PaginatedProgramList
}