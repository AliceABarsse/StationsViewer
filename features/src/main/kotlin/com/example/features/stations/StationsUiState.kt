package com.example.features.stations

import com.example.core.model.data.Station

sealed interface StationsUiState {
    object Loading : StationsUiState
    data class Error(val message: String) : StationsUiState

    object Empty : StationsUiState
    data class Success(val list:List<StationDetails>) : StationsUiState
}

data class StationDetails(
    val id: String,
    val name: String,
    val description: String,
    val tagline: String,
    val isLocal: Boolean
)
fun Station.toStationState() = StationDetails(
    id = id,
    name = id,
    description = description,
    tagline = baseline,
    isLocal = isLocal,
)