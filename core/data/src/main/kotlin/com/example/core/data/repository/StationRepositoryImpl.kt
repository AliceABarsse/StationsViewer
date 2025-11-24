package com.example.core.data.repository

import com.example.core.domain.network.ServiceApi
import com.example.core.domain.repository.StationRepository
import com.example.core.model.data.Station
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StationRepositoryImpl(
    private val networkDataSource: ServiceApi,
) : StationRepository {

    // Do not need to refresh very frequently
    private val refreshIntervalMs: Long = 1000 * 60 * 60 * 2

    override fun getStations(): Flow<List<Station>> =
        flow {
            while (true) {
                emit(networkDataSource.getStations())
                delay(refreshIntervalMs)
            }
        }
}