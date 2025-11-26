package com.example.features.programs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetProgramsUseCase
import com.example.core.model.data.Station
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgramsViewModel(
    private val getProgramsUseCase: GetProgramsUseCase,
) : ViewModel() {

    private val _programsState = MutableStateFlow<ProgramsUiState>(value = ProgramsUiState.Loading)
    val programsState: StateFlow<ProgramsUiState> = _programsState

    fun loadMorePrograms() {
        if (programsState.value is ProgramsUiState.Success) {
            val successValue = programsState.value as ProgramsUiState.Success
            if (successValue.lastCursor != null) {
                loadPrograms(
                    stationId = successValue.stationId,
                    stationName = successValue.stationName,
                    lastCursor = successValue.lastCursor,
                )
            }
        }

    }

    fun loadPrograms(station: Station, pageSize: Int = 10) {
        loadPrograms(stationId = station.id,
            stationName = station.title,
            pageSize = pageSize,
            )
    }

        private fun loadPrograms(stationId: String, stationName: String, pageSize: Int = 10, lastCursor: String? = null) {
        viewModelScope.launch {
            try {
                val programsList = getProgramsUseCase.invoke(
                    stationId = stationId,
                    pageSize = pageSize,
                    lastCursor = lastCursor
                )
                _programsState.value = if (programsList.list.isEmpty())
                    ProgramsUiState.Empty
                else
                    ProgramsUiState.Success(
                        stationId = stationId,
                        stationName = stationName,
                        lastCursor = programsList.lastCursor,
                        pageSize = programsList.pageSize,
                        list = programsList.list.map { it.toProgramDetails() })
            } catch (e: Exception) {
                _programsState.value = ProgramsUiState.Error(stationName = stationName, message = e.message ?: "Unknown error")
            }

        }
    }
}
