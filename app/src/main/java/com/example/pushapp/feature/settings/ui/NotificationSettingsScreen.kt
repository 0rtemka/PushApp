package com.example.pushapp.feature.settings.ui

import android.Manifest
import android.app.TimePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.pushapp.R
import com.example.pushapp.feature.settings.NotificationSettingsViewModel
import com.example.pushapp.ui.components.TopAppBar
import com.example.pushapp.ui.theme.PushAppTheme
import java.util.Calendar

@Composable
fun NotificationSettingsScreen(
    notificationSettingsViewModel: NotificationSettingsViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current

    // Начальное время, можно хранить в ViewModel
    val calendar = remember { Calendar.getInstance() }
    val hour = remember { mutableStateOf(calendar.get(Calendar.HOUR_OF_DAY)) }
    val minute = remember { mutableStateOf(calendar.get(Calendar.MINUTE)) }

// 1. Состояние нашего переключателя (в реальном приложении берите из DataStore/ViewModel)
    var isNotificationEnabled by remember {
        mutableStateOf(checkNotificationPermission(context))
    }

    // 2. Лаунчер для запроса разрешения
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            // Обновляем состояние свича по результату ответа пользователя
            isNotificationEnabled = isGranted
            if (!isGranted) {
                // Опционально: Можно показать SnackBar, что без прав уведомления не будут приходить
            }
        }
    )

    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _, selectedHour: Int, selectedMinute: Int ->
                hour.value = selectedHour
                minute.value = selectedMinute
            },
            hour.value,
            minute.value,
            true
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Профиль",
                onBackClick = onBackClick
            )
        },
        containerColor = Color.Transparent
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Уведомления",
                    style = MaterialTheme.typography.titleMedium
                )
                Switch(
                    checked = isNotificationEnabled,
                    onCheckedChange = { shouldBeEnabled ->
                        if (shouldBeEnabled) {
                            // Пользователь хочет ВКЛЮЧИТЬ
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                // Android 13+: Проверяем права
                                val permissionStatus = ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.POST_NOTIFICATIONS
                                )

                                if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
                                    isNotificationEnabled = true
                                } else {
                                    // Если прав нет — запрашиваем
                                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    // Важно: Свич пока не включаем, ждем onResult лаунчера!
                                }
                            } else {
                                // Android 12 и ниже: Права есть по умолчанию
                                isNotificationEnabled = true
                            }
                        } else {
                            // Пользователь хочет ВЫКЛЮЧИТЬ
                            // Просто меняем стейт UI. Отозвать права программно нельзя,
                            // можно только перестать слать пуши на бэкенде или локально.
                            isNotificationEnabled = false
                        }
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Время уведомления",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { timePickerDialog.show() },
                    shape = RoundedCornerShape(6.dp),
                    enabled = isNotificationEnabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f),
                        contentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp,
                            alignment = Alignment.CenterHorizontally
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.clock_icon),
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 4.dp),
                            contentDescription = "Clock",
                        )
                        Text(
                            text = String.format(
                                "%02d:%02d",
                                hour.value,
                                minute.value
                            ),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

            Button(
                onClick = { notificationSettingsViewModel.sendTestNotification() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                enabled = isNotificationEnabled
            ) {
                Text("Тестовое уведомление")
            }
        }
    }
}

fun checkNotificationPermission(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}

@Composable
@Preview(showSystemUi = true)
fun NotificationSettingsLightPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        NotificationSettingsScreen()
    }
}

@Composable
@Preview(showSystemUi = true)
fun NotificationSettingsDarkPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        NotificationSettingsScreen()
    }
}
