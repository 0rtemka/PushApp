package com.example.pushapp.feature.calendar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pushapp.R
import com.example.pushapp.feature.calendar.CalendarViewModel
import com.example.pushapp.ui.theme.PixelifySans
import com.example.pushapp.ui.theme.PushAppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CalendarSection(
    calendarViewModel: CalendarViewModel = hiltViewModel()
) {
    val daysOfWeek = listOf(
        stringResource(R.string.monday_short),
        stringResource(R.string.tuesday_short),
        stringResource(R.string.wednesday_short),
        stringResource(R.string.thursday_short),
        stringResource(R.string.friday_short),
        stringResource(R.string.saturday_short),
        stringResource(R.string.sunday_short),
    )

    val month by calendarViewModel.currentMonth.collectAsState()
    val days by calendarViewModel.days.collectAsState()
    val selectedDate by calendarViewModel.selectedDate.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { calendarViewModel.previousMonth() },
                shape = RoundedCornerShape(6.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onSurface,
                    contentColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Icon(
                    painter = painterResource(
                        R.drawable.arrow_icon
                    ),
                    contentDescription = "Previous month"
                )
            }

            Text(
                text = month.format(DateTimeFormatter.ofPattern("LLLL, yyyy", Locale("ru")))
                    .replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = { calendarViewModel.nextMonth() },
                shape = RoundedCornerShape(6.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.onSurface,
                    contentColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Icon(
                    painter = painterResource(
                        R.drawable.arrow_icon
                    ), contentDescription = "Next month",
                    modifier = Modifier.rotate(180f)
                )
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    daysOfWeek.forEach { day ->
                        Text(
                            text = day,
                            fontFamily = PixelifySans,
                            style = MaterialTheme.typography.titleSmall,
                            textAlign = TextAlign.Center,
                        )
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.Center,
                    userScrollEnabled = false
                ) {
                    items(days) { day ->
                        CalendarDay(
                            date = day,
                            isCurrentMonth = (day.month == month.month),
                            isToday = calendarViewModel.isToday(day),
                            selectedDate = selectedDate,
                            onDateSelected = {
                                calendarViewModel.selectDate(it)
                            }
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun CalendarDay(
    date: LocalDate,
    isCurrentMonth: Boolean,
    isToday: Boolean,
    selectedDate: LocalDate? = null,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    val isSelected = selectedDate == date
    val borderWidth = if (isSelected) 2.dp else 0.dp
    val circleSize = when {
        isToday -> 14.dp
        else -> 12.dp
    }
    Column(
        modifier = Modifier
            .padding(6.dp)
            .size(40.dp)
            .clip(CircleShape)
            .clickable(onClick = { onDateSelected(date) }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (isCurrentMonth) {
            val borderModifier = if (borderWidth > 0.dp) {
                Modifier.border(borderWidth, MaterialTheme.colorScheme.primary, CircleShape)
            } else {
                Modifier
            }

            Box(
                modifier = Modifier
                    .size(circleSize + 12.dp)
                    .then(borderModifier)
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(circleSize)
                        .background(
                            color = if (isToday)
                                MaterialTheme.colorScheme.primary
                            else
                                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CalendarSectionLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        CalendarSection()
    }
}

@Composable
@Preview(showBackground = true)
fun CalendarSectionDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        CalendarSection()
    }
}