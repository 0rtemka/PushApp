package com.example.pushapp.feature.trainingPreparation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class BatteryLevel {
    LOW, MEDIUM, HIGH
}

class TrainingPreparationViewModel : ViewModel() {
    private val _level = MutableStateFlow(BatteryLevel.HIGH)
    val level = _level.asStateFlow()

    fun setLevel(level: BatteryLevel) {
        _level.value = level
    }
}


