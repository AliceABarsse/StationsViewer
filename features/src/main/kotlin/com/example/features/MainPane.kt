package com.example.features

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.features.programs.ProgramsPane
import com.example.features.programs.ProgramsUiState
import com.example.features.stations.StationsPane
import com.example.features.stations.StationsUiState

@Composable
internal fun MainPane(
    stationsState: StationsUiState,
    modifier: Modifier = Modifier,
    programsState: ProgramsUiState,
    onClickStation: (String) -> Unit,
    onBackClick: () -> Unit,
    mainPaneState: MainPaneUiState,
) {
    BackHandler(enabled = mainPaneState is MainPaneUiState.ShowStationPrograms) {
        onBackClick()
    }

    when (mainPaneState) {

        is MainPaneUiState.ShowStations ->

            StationsPane(
                modifier = modifier,
                stationsState = stationsState,
                onClickStation = onClickStation,
            )

        is MainPaneUiState.ShowStationPrograms ->

            ProgramsPane(modifier = modifier, programsUiState = programsState)
    }


}