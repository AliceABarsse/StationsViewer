package com.example.features.stations

import java.util.UUID
import kotlin.random.Random

fun Random.nextStationState(
    id: String = UUID.randomUUID().toString(),
    isLocal: Boolean = nextBoolean()
) =
    StationState(
        id = id,
        name = id,
        description = UUID.randomUUID().toString(),
        tagline = UUID.randomUUID().toString(),
        isLocal = isLocal,
    )