package com.example.pushapp.feature.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.pushapp.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class NotificationSettingsViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    private val notificationService = NotificationService(context)

    fun sendTestNotification() {
        // Простая проверка прав перед отправкой (для надежности)
        // ... (код проверки из предыдущих шагов) ...

        notificationService.showBasicNotification(
            title = "Время тренировки",
            message = "Не забудьте сегодня выполнить тренировку!"
        )
    }
}