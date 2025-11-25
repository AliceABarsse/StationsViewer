package com.example.features.programs

import java.util.UUID
import kotlin.random.Random


fun Random.nextProgramsUiState() =
    ProgramsUiState.Success(
        list = List(size = nextInt(from=1, until = 10), init = {
            ProgramDetails(
                id = nextInt().toString(),
                name = UUID.randomUUID().toString(),
        )}),
        lastCursor = UUID.randomUUID().toString(),
    )
