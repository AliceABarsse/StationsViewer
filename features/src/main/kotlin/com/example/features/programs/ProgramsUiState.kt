package com.example.features.programs

import com.example.core.model.data.Program

sealed interface ProgramsUiState {
    object Loading : ProgramsUiState
    data class Error(val stationName: String, val message: String) : ProgramsUiState
    object Empty : ProgramsUiState
    data class Success(
        val stationId: String,
        val stationName: String,
        val list: List<ProgramDetails>,
        val lastCursor: String? = null,
        val pageSize: Int = 10,
    ) : ProgramsUiState
}

data class ProgramDetails(
    val id: String,
    val name: String,
)

fun Program.toProgramDetails() = ProgramDetails(
    id = id,
    name = title,
)