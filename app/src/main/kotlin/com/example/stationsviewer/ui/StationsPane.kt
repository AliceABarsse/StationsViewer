package com.example.stationsviewer.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp.Companion.Hairline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.core.model.data.KnownStations
import com.example.core.model.data.Station
import com.example.features.theme.StationsViewerTheme
import kotlin.random.Random

@Composable
fun StationsPane(modifier: Modifier = Modifier, stations: List<Station>) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = spacedBy(4.dp)
        ) {
        stations.map { station ->
            StationItemRow(
                station = station.toStationState(),
                modifier = Modifier.fillMaxWidth(),
                onClick = {},)
        }
    }
}

@Composable
internal fun StationItemRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    station: StationState,
) {
    Column {
        Row(
            modifier = modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .border(
                    width = Hairline, color = MaterialTheme.colorScheme.onSurface,
                )
                .padding(all = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(0.5f),
                text = station.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontSize = 3.em,
                fontStyle = FontStyle.Italic,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = station.description,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontSize = 2.em,
            )
        }
        Row (modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onSecondary,
                text = station.tagline,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontSize = 2.em,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}

@Preview
@Composable
private fun StationsPanePreview() {
    StationsViewerTheme {
        StationsPane(
            stations = Random.nextStationList(),
        )
    }
}

internal fun Random.nextStationList() = KnownStations.entries.map { stationId ->
    Station(
        id = stationId.name,
        isLocal = false,
        title = Random.nextInt().toString(),
        description = "Description",
        baseline = "Baseline",
    )
}