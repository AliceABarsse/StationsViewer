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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp.Companion.Hairline
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.features.R
import com.example.features.sharedui.TextCard

@Composable
fun StationsPane(
    onClickStation: (String) -> Unit,
    modifier: Modifier = Modifier,
    stationsState: StationsUiState = StationsUiState.Loading,
) {
    when (stationsState) {
        is StationsUiState.Error -> TextCard(text = stringResource(
            R.string.message_error,
            stationsState.message
        ),
            image = painterResource(id = R.drawable.grid_3x3_off_24px))
        StationsUiState.Loading -> TextCard(
            text = "Loading",
            image = painterResource(id = R.drawable.refresh_24px)
        )

        StationsUiState.Empty -> TextCard(
            text = "No Stations",
            image = painterResource(id = R.drawable.grid_3x3_off_24px)
        )

        is StationsUiState.Success -> StationsList(
            stations = stationsState.list,
            onClickStation = onClickStation,
            modifier = modifier,
        )

    }
}

@Composable
internal fun StationsList(
    onClickStation: (String) -> Unit,
    modifier: Modifier = Modifier,
    stations: List<StationDetails>
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
                    text = stringResource(R.string.label_list_radiostations),
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
        items(items = stations, key = { it.id }) { station ->
            StationItemRow(
                station = station,
                modifier = Modifier.Companion.animateItem(),
                onClick = { onClickStation(station.id) },
            )
        }
    }
}

@Composable
internal fun StationItemRow(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    station: StationDetails,
) {
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
        Row(
            modifier = modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .border(
                    width = Hairline,
                    color =
                        if (station.isLocal)
                            MaterialTheme.colorScheme.secondary
                        else MaterialTheme.colorScheme.primary,
                )
                .padding(all = 4.dp)
                .background(
                    color =
                        if (station.isLocal)
                            MaterialTheme.colorScheme.surfaceDim
                        else MaterialTheme.colorScheme.surface,
                )
                .padding(all = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f),
                text = station.name,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontSize = 4.em,
                fontStyle = FontStyle.Normal,
                color = MaterialTheme.colorScheme.primary,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .padding(horizontal = 8.dp)
                ,
                text = station.description,
                overflow = TextOverflow.Ellipsis,
                maxLines = 5,
                fontSize = 3.em,
                color = if (station.isLocal)
                    MaterialTheme.colorScheme.onSurfaceVariant
                else
                    MaterialTheme.colorScheme.onSurface
            )
        }
        if (station.tagline.isNotBlank()) {
            TaglineRow(station.tagline)
        }
    }
}

@Composable
private fun TaglineRow(tagline: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.tertiary)
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onTertiary,
            text = tagline,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            fontSize = 3.em,
            fontStyle = FontStyle.Italic,
        )
    }
}
