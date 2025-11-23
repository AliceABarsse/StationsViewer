package com.example.core.network

import com.example.core.network.model.NetworkBrand
import com.example.core.network.model.NetworkProgram

interface StationsViewerNetworkDataSource  {

    suspend fun getStations(): List<NetworkBrand>

    suspend fun getPrograms(stationId: String): List<NetworkProgram>
}