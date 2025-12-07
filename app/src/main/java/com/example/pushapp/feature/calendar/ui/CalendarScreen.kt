package com.example.pushapp.feature.calendar.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.feature.calendar.ui.components.CalendarSection
import com.example.pushapp.feature.calendar.ui.components.StatsSection
import com.example.pushapp.ui.components.TopAppBar
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun CalendarScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Календарь",
                onBackClick = onBackClick
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CalendarSection()
            StatsSection()
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun CalendarScreenLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        CalendarScreen()
    }
}

@Composable
@Preview(showSystemUi = true)
fun CalendarScreenDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        CalendarScreen()
    }
}


