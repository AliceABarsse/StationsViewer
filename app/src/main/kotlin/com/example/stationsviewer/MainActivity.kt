package com.example.stationsviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.core.model.data.nextStationsListFromKnownStations
import com.example.features.stations.StationsPane
import com.example.features.theme.StationsViewerTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StationsViewerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StationsPane(
                        modifier = Modifier.padding(innerPadding),
                        stations = Random.nextStationsListFromKnownStations(),
                        /*
                        // TODO Possible to show sub-stations in another panel
                        .filter { station ->
                        station.isLocal.not()
                    }*/
                        onClickStation = {},
                    )
                }
            }
        }
    }
}
