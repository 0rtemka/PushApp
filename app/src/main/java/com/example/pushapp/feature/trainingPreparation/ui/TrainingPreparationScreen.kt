package com.example.pushapp.feature.trainingPreparation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.feature.trainingPreparation.ui.components.Exercises
import com.example.pushapp.feature.trainingPreparation.ui.components.Intensity
import com.example.pushapp.feature.trainingPreparation.ui.components.TrainingInfo
import com.example.pushapp.ui.components.StartTrainingButton
import com.example.pushapp.ui.components.TopAppBar
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun TrainingPreparationScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onStartTrainingClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Тренировка",
                onBackClick = onBackClick
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Intensity()
            TrainingInfo()
            Exercises()
            Spacer(modifier = Modifier.weight(1f))
            StartTrainingButton(
                label = "Начать",
                onClick = onStartTrainingClick
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, name = "Light mode")
fun TrainingPreparationScreenLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        TrainingPreparationScreen()
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, name = "Dark mode")
fun TrainingPreparationScreenDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        TrainingPreparationScreen()
    }
}