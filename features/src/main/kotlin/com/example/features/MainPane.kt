package com.example.features

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.features.programs.ProgramsPane
import com.example.features.programs.ProgramsUiState
import com.example.features.sharedui.TextCard
import com.example.features.stations.StationsPane
import com.example.features.stations.StationsUiState

@Composable
internal fun MainPane(
    stationsState: StationsUiState,
    modifier: Modifier = Modifier,
    programsState: ProgramsUiState,
    onClickStation: (String) -> Unit,
    mainPaneState: MainPaneUiState,
) {

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