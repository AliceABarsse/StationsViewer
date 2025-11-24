package com.example.stationsviewer

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
import com.example.features.stations.StationsPane
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

                    val viewModel: StationsViewModel = koinViewModel()
                    val stationsState by viewModel.stationsState.collectAsStateWithLifecycle()

                    StationsPane(
                        modifier = Modifier.padding(innerPadding),
                        stationsState = stationsState,
                        onClickStation = {},
                    )
                }
            }
        }
    }
}
