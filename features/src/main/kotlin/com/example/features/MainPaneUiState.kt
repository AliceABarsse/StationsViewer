package com.example.features

sealed interface MainPaneUiState {
    object ShowStations : MainPaneUiState
    data class ShowStationPrograms(val stationId: String) : MainPaneUiState
}
