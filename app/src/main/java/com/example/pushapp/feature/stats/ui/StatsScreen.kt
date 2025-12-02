package com.example.pushapp.feature.stats.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.ui.components.TopAppBar
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun StatsScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Статистика",
                onBackClick = onBackClick
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
        ) {
            Text("StatsScreen")
        }
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true, name = "Light mode")
fun StatsScreenLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        StatsScreen()
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true, name = "Dark mode")
fun StatsScreenDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        StatsScreen()
    }
}