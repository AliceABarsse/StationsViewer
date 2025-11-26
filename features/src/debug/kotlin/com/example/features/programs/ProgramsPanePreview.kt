package com.example.features.programs

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.features.theme.StationsViewerTheme
import kotlin.random.Random


@Preview
@Composable
private fun ProgramsPanePreview() {
    StationsViewerTheme {
        ProgramsPane(
            programsUiState = Random.nextProgramsUiState(),
            onClickLoadMore = {},
        )
    }
}
