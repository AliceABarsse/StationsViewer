package com.example.features.stations

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
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
    fun `stationItemRow show the correct text`() {
        // Given
        val expectedStation = Random.nextStationDetail()

        // When
        composeTestRule.setContent {
            StationItemRow(
                onClick = {},
                station = expectedStation)
        }

        composeTestRule.onNodeWithText(substring = true, text = expectedStation.id, useUnmergedTree = false).assertIsDisplayed()
        composeTestRule.onNodeWithText(substring = true, text = expectedStation.name, useUnmergedTree = false).assertIsDisplayed()
        composeTestRule.onNodeWithText(substring = true, text = expectedStation.tagline, useUnmergedTree = false).assertIsDisplayed()
    }

}

