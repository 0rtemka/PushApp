package com.example.pushapp.feature.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pushapp.feature.training.utils.calculateTrainingStages
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class WorkoutScreen { Preparation, Work, Rest, Finished }

data class WorkoutState(
    val totalTime: Int = 0,
    val currentStepIndex: Int = 0,
    val steps: List<WorkoutScreen> = emptyList(),
    val currentScreen: WorkoutScreen = WorkoutScreen.Preparation,

    val preparationTime: Int = 10,
    val remainingPreparationTime: Int = 10,

    val restTime: Int = 120,
    val remainingRestTime: Int = 120,

    val isPreparationTimerStopped: Boolean = false,

    val pushUpsCount: Int = 16,
    val totalPushUpsCount: Int = 0,
)

class TrainingViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WorkoutState())
    val uiState = _uiState.asStateFlow()

    private var totalTimerJob: Job? = null
    private var stageTimerJob: Job? = null

    init {
        viewModelScope.launch {
            val steps = calculateTrainingStages(3)
            updateState { it.copy(steps = steps) }
            startPreparationTimer()
        }
    }

    private fun updateState(block: (WorkoutState) -> WorkoutState) {
        _uiState.value = block(_uiState.value)
    }

    fun startTotalTimer() {
        if (totalTimerJob?.isActive == true) return

        totalTimerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                updateState { it.copy(totalTime = it.totalTime + 1) }
            }
        }
    }

    fun stopTotalTimer() {
        totalTimerJob?.cancel()
        totalTimerJob = null
    }

    private fun startStageTimer(
        getter: (WorkoutState) -> Int,
        setter: (WorkoutState, Int) -> WorkoutState,
        onFinish: () -> Unit
    ) {
        stageTimerJob?.cancel()

        stageTimerJob = viewModelScope.launch {
            while (getter(_uiState.value) > 0) {
                delay(1000)

                updateState { state ->
                    val newValue = getter(state) - 1
                    setter(state, newValue)
                }
            }
            onFinish()
        }
    }

    fun startPreparationTimer() {
        updateState { it.copy(isPreparationTimerStopped = false) }

        startStageTimer(
            getter = { it.remainingPreparationTime },
            setter = { state, newValue -> state.copy(remainingPreparationTime = newValue) },
        ) {
            startTotalTimer()
            goToNextStep()
        }
    }

    fun stopPreparationTimer() {
        updateState { it.copy(isPreparationTimerStopped = true) }
        stageTimerJob?.cancel()
    }

    fun startRestTimer() {
        startStageTimer(
            getter = { it.remainingRestTime },
            setter = { state, newValue -> state.copy(remainingRestTime = newValue) }
        ) {
            goToNextStep()
        }
    }

    fun stopRestTimer() {
        stageTimerJob?.cancel()
        stageTimerJob = null

        updateState { it.copy(remainingRestTime = it.restTime) }
    }

    fun increaseRestTimer() {
        updateState { state ->
            state.copy(remainingRestTime = state.remainingRestTime + 30)
        }
    }

    fun decreaseRestTimer() {
        updateState { state ->
            val newValue = (state.remainingRestTime - 30).coerceAtLeast(0)
            state.copy(remainingRestTime = newValue)
        }
    }

    fun increasePushUps() {
        updateState { state ->
            state.copy(pushUpsCount = state.pushUpsCount + 1)
        }
    }

    fun decreasePushUps() {
        updateState { state ->
            val newValue = (state.pushUpsCount - 1).coerceAtLeast(0)
            state.copy(pushUpsCount = newValue)
        }
    }

    fun goToNextStep() {
        val state = _uiState.value
        val nextIndex = state.currentStepIndex + 1

        if (nextIndex >= state.steps.size) {
            _uiState.value = WorkoutState()
            return
        }

        val nextScreen = state.steps[nextIndex]

        if (nextScreen == WorkoutScreen.Rest) {
            updateState {
                it.copy(
                    totalPushUpsCount = it.totalPushUpsCount + it.pushUpsCount,
                    pushUpsCount = 16
                )
            }
            startRestTimer()
        }

        if (nextScreen == WorkoutScreen.Finished) {
            stopRestTimer()
            stopTotalTimer()
        }

        updateState {
            it.copy(
                currentStepIndex = nextIndex,
                currentScreen = nextScreen
            )
        }
    }
}
