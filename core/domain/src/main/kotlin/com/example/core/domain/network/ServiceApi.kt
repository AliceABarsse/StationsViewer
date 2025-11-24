package com.example.core.domain.network

import com.example.core.model.data.Program
import com.example.core.model.data.Station


interface ServiceApi  {

    suspend fun getStations(): List<Station>

    suspend fun getPrograms(stationId: String): List<Program>
}