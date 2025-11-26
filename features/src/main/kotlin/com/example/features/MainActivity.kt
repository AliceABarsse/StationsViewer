package com.example.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.domain.repository.ProgramRepository.Companion.MAX_PROGRAM_PAGE_SIZE
import com.example.features.programs.ProgramsViewModel
import com.example.features.stations.StationsViewModel
import com.example.features.theme.StationsViewerTheme
import org.koin.compose.viewmodel.koinViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StationsViewerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val mainPaneViewModel: MainPaneViewModel = koinViewModel()
                    val mainPaneState by mainPaneViewModel.mainPaneState.collectAsStateWithLifecycle()

                    val stationsViewModel: StationsViewModel = koinViewModel()
                    val stationsState by stationsViewModel.stationsState.collectAsStateWithLifecycle()

                    val programsViewModel: ProgramsViewModel = koinViewModel()
                    val programsState by programsViewModel.programsState.collectAsStateWithLifecycle()

                    MainPane(
                        modifier = Modifier.padding(innerPadding),
                        mainPaneState = mainPaneState,
                        stationsState = stationsState,
                        programsState = programsState,
                        onClickStation = { station ->
                            programsViewModel.loadPrograms(
                                station = station,
                                pageSize = MAX_PROGRAM_PAGE_SIZE,
                            )
                            mainPaneViewModel.setSelectedStation(stationId = station.id)
                        },
                        onBackClick = { mainPaneViewModel.unsetSelectedStation() },
                        onClickLoadMore = { programsViewModel::loadMorePrograms }
                    )
                }
            }
        }
    }
}