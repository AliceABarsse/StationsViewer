package com.example.features.stations

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.core.model.data.nextStationsListFromKnownStations
import com.example.features.theme.StationsViewerTheme
import kotlin.random.Random

@Preview
@Composable
private fun StationsPanePreview() {
    StationsViewerTheme {
        StationsPane(
            stations = Random.nextStationsListFromKnownStations(),
            onClickStation = {},
        )
    }
}
