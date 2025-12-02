package com.example.pushapp.feature.training.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.feature.training.WorkoutScreen
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun TrainingSteps(steps: List<WorkoutScreen>, currentStepIndex: Int = 0) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
    ) {
        steps.forEachIndexed { index, step ->
            TrainingStep(
                step,
                isActive = currentStepIndex == index,
                isPassed = currentStepIndex > index
            )
        }
    }
}

@Composable
fun TrainingStep(step: WorkoutScreen, isActive: Boolean = false, isPassed: Boolean = false) {
    Card(
        modifier = Modifier.size(36.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isPassed) MaterialTheme.colorScheme.secondary else if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            contentColor = if (isPassed) MaterialTheme.colorScheme.onSecondary else if (isActive) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
        ),
        shape = RoundedCornerShape(4.dp),
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when (step) {
                WorkoutScreen.Preparation -> Icon(
                    painter = painterResource(R.drawable.clock_icon),
                    contentDescription = "Подготовка",
                    modifier = Modifier.size(16.dp)
                )

                WorkoutScreen.Work -> Icon(
                    painter = painterResource(R.drawable.lift_icon),
                    contentDescription = "Тренировка",
                    modifier = Modifier.size(20.dp)
                )

                WorkoutScreen.Rest -> Icon(
                    painter = painterResource((R.drawable.clock_icon)),
                    contentDescription = "Отдых",
                    modifier = Modifier.size(16.dp)
                )

                WorkoutScreen.Finished -> Icon(
                    painter = painterResource(R.drawable.check_icon),
                    contentDescription = "Тренировка завершена",
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

val stepsMock = listOf(
    WorkoutScreen.Preparation,
    WorkoutScreen.Work,
    WorkoutScreen.Rest,
    WorkoutScreen.Finished
)

@Composable
@Preview(showBackground = false)
fun TrainingStepsLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        TrainingSteps(
            steps = stepsMock
        )
    }
}

@Composable
@Preview(showBackground = false)
fun TrainingStepsDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        TrainingSteps(
            steps = stepsMock
        )
    }
}


