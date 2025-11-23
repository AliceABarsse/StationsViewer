package com.example.stationsviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.stationsviewer.ui.StationsPane
import com.example.stationsviewer.ui.nextStationList
import com.example.stationsviewer.ui.theme.StationsViewerTheme
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
                        stations = Random.nextStationList(),
                    )
                }
            }
        }
    }
}
