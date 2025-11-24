package com.example.core.domain.usecase

import com.example.core.domain.repository.StationRepository
import com.example.core.model.data.Station
import kotlinx.coroutines.flow.Flow

class GetStationsUseCase(private val dataSource: StationRepository) {
    operator fun invoke(): Flow<List<Station>> {
        return dataSource.getStations()
    }
}
