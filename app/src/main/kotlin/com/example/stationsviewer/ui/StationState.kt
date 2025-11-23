package com.example.stationsviewer.ui

import com.example.core.model.data.Station
import kotlin.String

data class StationState(val id: String, val name: String, val description: String, val tagline: String)

fun Station.toStationState() = StationState(
    id = id,
    name = id,
    description = description,
    tagline = baseline)