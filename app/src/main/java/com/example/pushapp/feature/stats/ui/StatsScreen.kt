package com.example.pushapp.feature.profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun StatsScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text("StatsScreen")
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