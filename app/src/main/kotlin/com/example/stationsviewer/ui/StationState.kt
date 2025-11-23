package com.example.stationsviewer.ui

import com.example.core.model.data.Station

data class StationState(
    val id: String,
    val name: String,
    val description: String,
    val tagline: String,
    val isLocal: Boolean
)

fun Station.toStationState() = StationState(
    id = id,
    name = id,
    description = description,
    tagline = baseline,
    isLocal = isLocal,
)