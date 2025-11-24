package com.example.features.stations

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random

@RunWith(AndroidJUnit4::class)
class StationsPaneTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun stationsPane() {
    }

    @Test
    fun `stationItemRow show the correct text`() {
        // Given
        val expectedStation = Random.nextStationState()

        // When
        composeTestRule.setContent {
            StationItemRow(
                onClick = {},
                station = expectedStation)
        }
    }

}