package com.example.core.model.data

import java.util.UUID
import kotlin.random.Random

fun Random.nextProgram(
    id: String = nextInt().toString(),
) =
    Program(
        id = id,
        title = UUID.randomUUID().toString(),
    )
