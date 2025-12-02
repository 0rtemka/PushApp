package com.example.pushapp.feature.training.utils

import com.example.pushapp.feature.training.WorkoutScreen

fun calculateTrainingStages(sets: Int): List<WorkoutScreen> {
    val stages = mutableListOf<WorkoutScreen>()

    stages += WorkoutScreen.Preparation

    repeat(sets) { index ->
        stages += WorkoutScreen.Work
        stages += WorkoutScreen.Rest
    }

    stages += WorkoutScreen.Finished

    return stages
}
