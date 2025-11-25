package com.example.features.programs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.GetProgramsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgramsViewModel(
    private val getProgramsUseCase: GetProgramsUseCase,
) : ViewModel() {

    private val _programsState = MutableStateFlow<ProgramsUiState>(value = ProgramsUiState.Loading)
    val programsState: StateFlow<ProgramsUiState> = _programsState

    fun loadPrograms(stationId: String, pageSize: Int = 10, lastCursor: String? = null) {
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
                        lastCursor = programsList.lastCursor,
                        pageSize = programsList.pageSize,
                        list = programsList.list.map { it.toProgramDetails() })
            } catch (e: Exception) {
                _programsState.value = ProgramsUiState.Error(message = e.message ?: "Unknown error")
            }

        }
    }
}
