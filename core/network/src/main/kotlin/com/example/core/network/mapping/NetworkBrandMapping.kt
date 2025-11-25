package com.example.core.network.mapping

import com.example.core.model.data.Station
import com.example.core.network.model.LocalRadio
import com.example.core.network.model.NetworkBrand

fun NetworkBrand.toStation(): List<Station> {
    val list = mutableListOf<Station>()
    list.add(
    Station(
        id = id,
        title = title,
        description = description ?: "",
        baseline = baseline ?: "",
        isLocal = false,
    ))
    localRadios?.forEach {
        list.add(it.toStation())
    }
    return list
}

fun LocalRadio.toStation(): Station =
    Station(
        id = id,
        title = title,
        description = description ?: "",
        baseline = "",
        isLocal = true,
    )
