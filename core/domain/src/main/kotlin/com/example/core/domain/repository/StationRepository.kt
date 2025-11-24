package com.example.core.domain.repository

import com.example.core.model.data.Station
import kotlinx.coroutines.flow.Flow

interface StationRepository {
    fun getStations(): Flow<List<Station>>
}