package com.example.features.stations

import java.util.UUID
import kotlin.random.Random

fun Random.nextStationDetail(
    id: String = UUID.randomUUID().toString(),
    isLocal: Boolean = nextBoolean()
) =
    StationDetails(
        id = id,
        name = UUID.randomUUID().toString(),
        description = UUID.randomUUID().toString(),
        tagline = UUID.randomUUID().toString(),
        isLocal = isLocal,
    )