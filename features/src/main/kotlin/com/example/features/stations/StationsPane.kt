package com.example.features.stations

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
fun StationsPane(
    onClickStation: (String) -> Unit,
    modifier: Modifier = Modifier,
    stations: List<Station> = emptyList(),
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(8.dp)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
    ) {
        stickyHeader {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxWidth()
                    .height(32.dp)
            ) {
                Text(
                    text = "Stations de radio",
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
        items(items = stations, key = { it.id }) { station ->
            StationItemRow(
                station = station.toStationState(),
                modifier = Modifier.Companion.animateItem(),
                onClick = { onClickStation(station.id) },
            )
        }
    }
}

@Composable
fun StationItemRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    station: StationState,
) {
    Column (modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
        Row(
            modifier = modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .border(
                    width = Hairline, color = MaterialTheme.colorScheme.primary,
                )
                .background(color = MaterialTheme.colorScheme.background)
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(0.5f),
                text = station.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontSize = 3.em,
                fontStyle = FontStyle.Normal,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = station.description,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontSize = 2.em,
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.tertiary)
                .padding(horizontal = 16.dp, vertical = 8.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onTertiary,
                text = station.tagline,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontSize = 2.em,
                fontStyle = FontStyle.Italic,
            )
        }
    }
}
