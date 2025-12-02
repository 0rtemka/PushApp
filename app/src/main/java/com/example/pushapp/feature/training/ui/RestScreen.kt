package com.example.pushapp.feature.training.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.feature.training.ui.components.SkipButton
import com.example.pushapp.feature.training.ui.components.StageTitle
import com.example.pushapp.feature.training.ui.components.TrainingTimer
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun RestScreen(sec: Int, onSkipClick: () -> Unit, onIncreaseClick: () -> Unit, onDecreaseClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        StageTitle(title = "Отдых")
        Spacer(modifier = Modifier.weight(1f))
        TrainingTimer(sec = sec, onIncreaseClick = onIncreaseClick, onDecreaseClick = onDecreaseClick)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SkipButton(modifier = Modifier.weight(1f), onClick = onSkipClick)
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RestScreenLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        RestScreen(sec = 228, onSkipClick = {}, onIncreaseClick = {}, onDecreaseClick = {})
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun RestScreenDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        RestScreen(sec = 228, onSkipClick = {}, onIncreaseClick = {}, onDecreaseClick = {})
    }
}