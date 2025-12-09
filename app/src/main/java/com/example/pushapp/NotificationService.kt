package com.example.pushapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat

class NotificationService(
    private val context: Context
) {
    // ID канала должен быть уникальным в рамках приложения
    private val channelId = "trainings_notification_channel"
    private val notificationId =
        1 // ID уведомления (если слать с одним ID, они будут перезаписывать друг друга)

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // 1. Создаем канал (вызывается один раз, например, при старте приложения)
    fun createNotificationChannel() {
        val channel = NotificationChannel(
            channelId,
            "Уведомления о тренировках",
            NotificationManager.IMPORTANCE_HIGH // Важность (звук, вибрация)
        ).apply {
            description = "Напоминания о необходимости выполнить тренировку"
        }
        notificationManager.createNotificationChannel(channel)
    }

    // 2. Метод отправки уведомления
    fun showBasicNotification(title: String, message: String) {
        // Intent описывает, что открыть при клике на уведомление
        val activityIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        // PendingIntent - "отложенный" интент, который система запустит от имени нашего приложения
        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            // IMMUTABLE обязателен для Android 12+ (API 31)
            PendingIntent.FLAG_IMMUTABLE
        )

        val largeIconBitmap = BitmapFactory.decodeResource(
            context.resources,
            R.mipmap.ic_launcher
        )

        // Конструируем уведомление
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.classic_pushup) // Твоя иконка (белая!)
            .setLargeIcon(largeIconBitmap)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Для совместимости с Android < 8.0
            .setContentIntent(pendingIntent) // Действие по клику
            .setAutoCancel(true) // Убирать уведомление после клика
            .build()

        // Показываем
        // В реальном коде тут стоит проверить checkSelfPermission,
        // но мы предполагаем, что права уже проверены во ViewModel
        notificationManager.notify(notificationId, notification)
    }
}