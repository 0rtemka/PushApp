package com.example.pushapp.feature.training.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.R
import com.example.pushapp.ui.theme.Lora
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun TrainingStats(
    time: String,
    pushUps: String,
    kcal: String,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrainingStat(icon = R.drawable.clock_icon, title = time, description = "минут")
            TrainingStat(icon = R.drawable.lift_icon, title = pushUps, description = "отжиманий")
            TrainingStat(icon = R.drawable.fire_icon, title = kcal, description = "ккал")
        }
    }
}

@Composable
fun TrainingStat(
    icon: Int,
    title: String,
    description: String,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(22.dp),
            tint = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = Lora,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TrainingStatsLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        TrainingStats(time = "15", pushUps = "121", kcal = "58")
    }
}

@Composable
@Preview(showBackground = true)
fun TrainingStatsDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        TrainingStats(time = "15", pushUps = "121", kcal = "58")
    }
}