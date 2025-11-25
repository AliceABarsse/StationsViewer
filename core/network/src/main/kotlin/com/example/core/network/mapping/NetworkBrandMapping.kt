package com.example.core.network.mapping

import com.example.core.model.data.Station
import com.example.core.network.model.NetworkBrand

fun NetworkBrand.toStation(): Station =
    Station(
        id = id,
        title = title,
        description = description ?: "",
        baseline = baseline ?: "",
        isLocal = localRadios?.isEmpty()?.not() ?: false,
    )