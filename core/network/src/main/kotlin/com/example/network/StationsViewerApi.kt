package com.example.network

import com.example.network.model.NetworkProgram
import com.example.network.model.NetworkBrand

interface StationsViewerNetworkDataSource  {

    suspend fun getStations(): List<NetworkBrand>

    suspend fun getPrograms(stationId: String): List<NetworkProgram>
}
