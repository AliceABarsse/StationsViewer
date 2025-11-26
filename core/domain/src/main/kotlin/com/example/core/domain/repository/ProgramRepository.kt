package com.example.core.domain.repository

import com.example.core.model.data.PaginatedProgramList

interface ProgramRepository {

    companion object {
        const val MAX_PROGRAM_PAGE_SIZE = 99
    }

    suspend fun getPrograms(stationId: String, pageSize: Int = 10, lastCursor: String? = null): PaginatedProgramList
}