package com.example.stationsviewer.ui

import com.example.core.model.data.Station
import com.example.features.stations.toStationState
import org.junit.Assert.*
import org.junit.Test
import java.util.UUID
import kotlin.random.Random

class StationStateTest {

    /**
     * Mapping extension function maps values to new fields.
     */
    @Test
    fun `toStationState properly maps domain object fields to UI state object's fields`() {
        // Given
        val initialValue = Random.nextStation( id= "MY TEST")

        // When
        val result = initialValue.toStationState()

        // Then
        assertEquals(initialValue.id, result.id)
        assertEquals(initialValue.id, result.name)
        assertEquals(initialValue.isLocal, result.isLocal)
        assertEquals(initialValue.description, result.description)
        assertEquals(initialValue.baseline, result.tagline)
    }
}

/**
 * Generate with Random values for all attributes.
 */
fun Random.nextStation(
    id: String = UUID.randomUUID().toString(),
) = Station(
    id = id,
    title = UUID.randomUUID().toString(),
    isLocal = nextBoolean(),
    description = UUID.randomUUID().toString(),
    baseline = UUID.randomUUID().toString(),
)
