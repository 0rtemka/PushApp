package com.example.pushapp.feature.training.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pushapp.feature.training.TrainingViewModel
import com.example.pushapp.feature.training.WorkoutScreen
import com.example.pushapp.feature.training.ui.components.HeaderTimer
import com.example.pushapp.feature.training.ui.components.TrainingSteps

@Composable
fun TrainingHostScreen(
    modifier: Modifier = Modifier,
    viewModel: TrainingViewModel = hiltViewModel(),
    onReturnHome: () -> Unit = {},
) {
    val state by viewModel.uiState.collectAsState()

    var showExitDialog by remember { mutableStateOf(false) }

    BackHandler {
        if (state.currentScreen == WorkoutScreen.Finished) {
            onReturnHome()
        } else {
            showExitDialog = true

            when (state.currentScreen) {
                WorkoutScreen.Preparation -> viewModel.stopPreparationTimer()
                WorkoutScreen.Rest -> viewModel.stopRestTimer()
                else -> viewModel.stopTotalTimer()
            }
        }
    }

    if (showExitDialog) {
        AlertDialog(
            shape = RoundedCornerShape(12.dp),
            onDismissRequest = { showExitDialog = false },
            title = {
                Text(
                    text = "Завершить тренировку?",
                    style = MaterialTheme.typography.headlineSmall
                )
            },
            text = {
                Text(
                    text = "Прогресс будет сброшен. Точно хотите выйти?",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showExitDialog = false
                        onReturnHome()
                    }
                ) {
                    Text("Да")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showExitDialog = false

                        when (state.currentScreen) {
                            WorkoutScreen.Preparation -> viewModel.startPreparationTimer()
                            WorkoutScreen.Rest -> viewModel.startRestTimer()
                            else -> viewModel.startTotalTimer()
                        }
                    }
                ) {
                    Text("Нет")
                }
            }
        )
    }

    Scaffold() { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp)
        ) {
            if (state.currentScreen != WorkoutScreen.Finished) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterVertically)
                ) {
                    HeaderTimer(state.totalTime)
                    TrainingSteps(steps = state.steps, currentStepIndex = state.currentStepIndex)
                }
            }
            when (state.currentScreen) {
                WorkoutScreen.Preparation -> PreparationScreen(
                    sec = state.remainingPreparationTime,
                    isPaused = state.isPreparationTimerStopped,
                    onPlayClick = if (state.isPreparationTimerStopped) viewModel::startPreparationTimer else viewModel::stopPreparationTimer,
                    onSkipClick = {
                        viewModel.stopPreparationTimer()
                        viewModel.startTotalTimer()
                        viewModel.goToNextStep()
                    }
                )

                WorkoutScreen.Work -> WorkScreen(
                    reps = state.pushUpsCount,
                    onIncreaseClick = viewModel::increasePushUps,
                    onDecreaseClick = viewModel::decreasePushUps,
                    onDoneClick = viewModel::goToNextStep
                )

                WorkoutScreen.Rest -> RestScreen(
                    sec = state.remainingRestTime,
                    onIncreaseClick = viewModel::increaseRestTimer,
                    onDecreaseClick = viewModel::decreaseRestTimer,
                    onSkipClick = {
                        viewModel.stopRestTimer()
                        viewModel.goToNextStep()
                    },
                )

                WorkoutScreen.Finished -> FinishScreen(
                    totalTime = state.totalTime,
                    totalReps = state.totalPushUpsCount,
                    onReturnHome = onReturnHome,
                )
            }
        }
    }
}