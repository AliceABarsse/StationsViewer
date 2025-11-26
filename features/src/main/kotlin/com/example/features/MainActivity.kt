package com.example.features

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
                        onClickStation = { stationId ->
                            programsViewModel.loadPrograms(stationId = stationId)
                            mainPaneViewModel.setSelectedStation(stationId = stationId)
                        },
                        onBackClick = { mainPaneViewModel.unsetSelectedStation() }
                    )
                }
            }
        }
    }
}