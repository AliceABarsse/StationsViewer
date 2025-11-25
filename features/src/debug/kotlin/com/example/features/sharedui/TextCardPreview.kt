package com.example.features.sharedui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.features.theme.StationsViewerTheme

@Preview
@Composable
private fun TextCardPreview() {
    StationsViewerTheme {
        TextCard(text = "Message")
    }
}