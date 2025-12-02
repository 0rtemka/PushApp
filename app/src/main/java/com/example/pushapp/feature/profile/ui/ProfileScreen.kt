package com.example.pushapp.feature.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.pushapp.feature.profile.ui.components.AwardsSwiper
import com.example.pushapp.feature.profile.ui.components.SettingsMenu
import com.example.pushapp.ui.components.UserAvatar
import com.example.pushapp.ui.components.TopAppBar
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = "Профиль",
                onBackClick = onBackClick
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = innerPadding.calculateTopPadding())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserAvatar()
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