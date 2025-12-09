package com.example.pushapp.feature.settings.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pushapp.feature.settings.Gender
import com.example.pushapp.feature.settings.UserSettingsViewModel
import com.example.pushapp.ui.components.TopAppBar
import com.example.pushapp.ui.theme.PushAppTheme

@Composable
fun UserSettingsScreen(
    viewModel: UserSettingsViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val name = viewModel.name
    val gender = viewModel.gender

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Профиль",
                onBackClick = onBackClick
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Поле ввода имени
            OutlinedTextField(
                value = name,
                onValueChange = { viewModel.onNameChange(it) },
                label = { Text("Имя") },
                modifier = Modifier.fillMaxWidth()
            )

            // Выбор пола
            Text(
                text = "Пол",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Gender.entries.forEach { option ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.onGenderChange(option) }
                        .padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = gender == option,
                        onClick = { viewModel.onGenderChange(option) }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(option.label)
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                onClick = { viewModel.saveUserData() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = RoundedCornerShape(6.dp),
                enabled = name.isNotBlank() && gender != null
            ) {
                Text("Сохранить", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun UserSettingsLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        UserSettingsScreen()
    }
}

@Composable
@Preview(showSystemUi = true)
fun UserSettingsDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        UserSettingsScreen()
    }
}
