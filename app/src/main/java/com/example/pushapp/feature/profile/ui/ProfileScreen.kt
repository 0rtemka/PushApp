package com.example.pushapp.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pushapp.feature.profile.ui.components.AwardsSwiper
import com.example.pushapp.feature.profile.ui.components.SettingsMenu
import com.example.pushapp.feature.profile.ui.components.UserInfo
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserInfo()
            AwardsSwiper()
            SettingsMenu()
        }
    }
}

@Composable
@Preview(showSystemUi = true, name = "Light mode", apiLevel = 34)
fun ProfileScreenLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        ProfileScreen()
    }
}

@Composable
@Preview(showSystemUi = true, name = "Dark mode", apiLevel = 34)
fun ProfileScreenDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        ProfileScreen()
    }
}