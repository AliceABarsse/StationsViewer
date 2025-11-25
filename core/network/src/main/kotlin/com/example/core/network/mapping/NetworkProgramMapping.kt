package com.example.core.network.mapping

import com.example.core.model.data.Program
import com.example.core.network.model.NetworkShow


fun NetworkShow.toProgram(): Program =
    Program(
        id = id,
        title = title,
    )
