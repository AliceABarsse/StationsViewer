package com.example.core.domain.usecase

import com.example.core.domain.repository.ProgramRepository
import com.example.core.model.data.PaginatedProgramList

class GetProgramsUseCase(private val dataSource: ProgramRepository) {

    suspend operator fun invoke(
        stationId: String,
        pageSize: Int = 10,
        lastCursor: String? = null,
    ): PaginatedProgramList =
        dataSource.getPrograms(stationId, pageSize = pageSize, lastCursor = lastCursor)
}
