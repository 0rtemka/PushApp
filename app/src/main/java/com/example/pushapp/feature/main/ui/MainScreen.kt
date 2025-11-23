package com.example.pushapp.feature.main.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pushapp.R
import com.example.pushapp.feature.main.MainViewModel
import com.example.pushapp.feature.main.ui.components.ActivityCalendar
import com.example.pushapp.feature.main.ui.components.PushUpsCounter
import com.example.pushapp.feature.main.ui.components.StartButton
import com.example.pushapp.feature.main.ui.components.UserInfo
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val user by mainViewModel.user.collectAsState()

    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PushUpsCounter(
                    painterResource(R.drawable.lift_icon),
                    "100",
                    "Отжиманий",
                    10.dp,
                    Modifier.weight(1f)
                )
                PushUpsCounter(
                    painterResource(R.drawable.fire_icon),
                    "332",
                    "Калории",
                    50.dp,
                    Modifier.weight(1f)
                )
                PushUpsCounter(
                    painterResource(R.drawable.target_icon),
                    "14",
                    "Дней",
                    30.dp,
                    Modifier.weight(1f)
                )
            }
            ActivityCalendar()
            Spacer(modifier = Modifier.weight(1f))
            UserInfo()
            Spacer(modifier = Modifier.weight(1f))
            StartButton(
                label = "Начать",
                helperLabel = "ежедневная тренировка",
                timeLabel = "15 минут"
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Light Mode", apiLevel = 34)
@Composable
fun MainScreenLightModePreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        MainScreen()
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Dark Mode", apiLevel = 34)
@Composable
fun MainScreenDarkModePreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        MainScreen()
    }
}
