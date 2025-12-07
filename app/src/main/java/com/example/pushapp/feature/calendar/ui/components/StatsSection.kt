package com.example.pushapp.feature.calendar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pushapp.R
import com.example.pushapp.feature.calendar.CalendarViewModel
import com.example.pushapp.ui.components.TrainingStatItem
import com.example.pushapp.ui.theme.Lora
import com.example.pushapp.ui.theme.PushAppTheme
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun StatsSection(
    calendarViewModel: CalendarViewModel = hiltViewModel()
) {
    val selectedDate by calendarViewModel.selectedDate.collectAsState()

    val day = selectedDate.dayOfMonth
    val monthName = selectedDate.format(
        DateTimeFormatter.ofPattern("MMMM", Locale("ru"))
    ).replaceFirstChar { it.uppercase() }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(
                12.dp,
                Alignment.CenterVertically
            )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = day.toString(),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = monthName,
                    style = MaterialTheme.typography.headlineLarge,
                )
            }
            TrainingsList()
            TrainingStats()
        }
    }
}

@Composable
fun TrainingsList() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Тренировки",
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = Lora,
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            TrainingListItem()
            TrainingListItem()
            TrainingListItem()
        }
    }
}

@Composable
fun TrainingListItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "10:00",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.onSurface)
        )
        Text(
            text = "10 минут",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.onSurface)
        )
        Text(
            text = "99 отжиманий",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
    }
}

@Composable
fun TrainingStats() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Статистика",
            style = MaterialTheme.typography.headlineSmall,
            fontFamily = Lora,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TrainingStatItem(
                icon = R.drawable.clock_icon,
                title = "32",
                description = "минут"
            )
            TrainingStatItem(
                icon = R.drawable.lift_icon,
                title = "121",
                description = "отжимание"
            )
            TrainingStatItem(
                icon = R.drawable.clock_icon,
                title = "54",
                description = "ккал"
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StatsSectionLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        StatsSection()
    }
}

@Composable
@Preview(showBackground = true)
fun StatsSectionDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        StatsSection()
    }
}