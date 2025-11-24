package com.example.features.stations

import com.example.core.model.data.Station

sealed interface StationUiState {
    object Loading : StationUiState
    data class Error(val message: String) : StationUiState

    object Empty : StationUiState
    data class Success(val list:List<StationDetail>) : StationUiState
}

data class StationDetail(
    val id: String,
    val name: String,
    val description: String,
    val tagline: String,
    val isLocal: Boolean
)
fun Station.toStationState() = StationDetail(
    id = id,
    name = id,
    description = description,
    tagline = baseline,
    isLocal = isLocal,
)