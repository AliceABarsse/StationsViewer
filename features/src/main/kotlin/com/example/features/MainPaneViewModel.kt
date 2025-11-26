package com.example.features

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainPaneViewModel() : ViewModel() {

    private val _mainPaneState: MutableStateFlow<MainPaneUiState> = MutableStateFlow(MainPaneUiState.ShowStations)
    val mainPaneState: StateFlow<MainPaneUiState> = _mainPaneState

    internal fun setSelectedStation(stationId: String) {
        viewModelScope.launch {
            _mainPaneState.value = MainPaneUiState.ShowStationPrograms(stationId = stationId)
        }
    }

    internal fun unsetSelectedStation() {
        viewModelScope.launch {
            _mainPaneState.value = MainPaneUiState.ShowStations
        }
    }

}