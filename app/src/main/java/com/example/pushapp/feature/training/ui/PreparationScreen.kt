package com.example.pushapp.feature.training.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.feature.training.ui.components.SkipButton
import com.example.pushapp.feature.training.ui.components.StageTitle
import com.example.pushapp.feature.training.ui.components.TrainingTimer
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun PreparationScreen(
    sec: Int,
    isPaused: Boolean,
    onPlayClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        StageTitle(title = "Подготовка")
        Spacer(modifier = Modifier.weight(1f))
        TrainingTimer(sec = sec)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SkipButton(modifier = Modifier.weight(1f), onClick = onSkipClick)
            PlayButton(modifier = Modifier.weight(1f), isPaused = isPaused, onClick = onPlayClick)
        }
    }
}

@Composable
fun PlayButton(modifier: Modifier = Modifier, onClick: () -> Unit, isPaused: Boolean = true) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(70.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        if (isPaused)
            Icon(
                painter = painterResource(R.drawable.play_icon),
                contentDescription = "Возобновить"
            )
        else
            Icon(
                painter = painterResource(R.drawable.pause_icon),
                contentDescription = "Пауза"
            )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreparationScreenLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        PreparationScreen(sec = 228, isPaused = true, onPlayClick = {}, onSkipClick = {})
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreparationScreenDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        PreparationScreen(sec = 228, isPaused = true, onPlayClick = {}, onSkipClick = {})
    }
}

