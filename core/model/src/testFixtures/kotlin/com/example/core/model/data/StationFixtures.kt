package com.example.core.model.data

import java.util.UUID
import kotlin.random.Random

fun Random.nextStation(
    id: String = UUID.randomUUID().toString(),
    isLocal: Boolean = nextBoolean()
) =
    Station(
        id = id,
        title = id,
        description = UUID.randomUUID().toString(),
        baseline = UUID.randomUUID().toString(),
        isLocal = isLocal,
    )
