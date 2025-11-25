package com.example.features.stations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetStationsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StationsViewModel(
    getStationsUseCase: GetStationsUseCase
) : ViewModel() {
    val stationsState: StateFlow<StationsUiState> =
        getStationsUseCase().map { stationsList ->
            if (stationsList.isEmpty())
                StationsUiState.Empty
            else
                StationsUiState.Success(list = stationsList.map { it.toStationState() })
        }
            .stateIn(
                scope = viewModelScope,
                initialValue = StationsUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            )
}
