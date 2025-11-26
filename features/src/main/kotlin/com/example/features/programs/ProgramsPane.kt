package com.example.features.programs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.features.R
import com.example.features.sharedui.StickyListHeader
import com.example.features.sharedui.TextCard

@Composable
fun ProgramsPane(
    modifier: Modifier = Modifier,
    programsUiState: ProgramsUiState = ProgramsUiState.Loading,
    onClickLoadMore: () -> Unit,
) {
    when (programsUiState) {

        is ProgramsUiState.Error -> TextCard(
            text = stringResource(
                R.string.message_error,
                "${programsUiState.message} (${programsUiState.stationName})",
            ),
            image = painterResource(id = R.drawable.grid_3x3_off_24px),
        )

        ProgramsUiState.Loading -> TextCard(
            text = "Loading",
            image = painterResource(id = R.drawable.refresh_24px),
        )

        ProgramsUiState.Empty -> TextCard(
            text = "No Stations",
            image = painterResource(id = R.drawable.grid_3x3_off_24px),
        )

        is ProgramsUiState.Success -> ProgramsList(
            programs = programsUiState.list,
            modifier = modifier,
            stationId = programsUiState.stationName,
            onClickLoadMore = onClickLoadMore,
        )
    }
}

@Composable
internal fun ProgramsList(
    modifier: Modifier = Modifier,
    stationId: String,
    programs: List<ProgramDetails>,
    onClickLoadMore: () -> Unit,
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
            StickyListHeader(text = stringResource(R.string.label_list_withname, stationId))
        }
        items(items = programs, key = { it.id }) { programDetails ->
            ProgramRow(
                programDetails = programDetails,
                modifier = Modifier.Companion.animateItem(),
            )
        }
        item(key = "load-more") {
            Button(onClick = onClickLoadMore) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Charger plus de programmes",
                    textAlign = TextAlign.Center,
                )
            }

        }
    }
}

@Composable
internal fun ProgramRow(
    modifier: Modifier = Modifier,
    programDetails: ProgramDetails,
) {
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.surface)) {
        OutlinedCard {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                    )
                    .padding(all = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = programDetails.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    fontSize = 4.em,
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

