package com.example.features.stations

import com.example.core.model.data.KnownStations
import kotlin.random.Random

fun Random.nextStationsStateListFromKnownStations() =
    StationsUiState.Success(list =
        KnownStations.entries.map { stationId ->
            StationDetails(
                id = stationId.name,
                isLocal = nextBoolean(),
                name = nextInt().toString(),
                description = "Description",
                tagline = "Baseline",
            )
        })
