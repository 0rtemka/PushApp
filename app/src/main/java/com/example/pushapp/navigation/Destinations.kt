package com.example.pushapp.navigation

import com.example.pushapp.R

sealed class Screen(val route: String, val title: String?, val iconRes: Int?) {
    data object Home : Screen("home", "Главная", R.drawable.home_icon)
    data object Stats : Screen("stats", "Статистика", R.drawable.stats_icon)
    data object TrainingPreparation : Screen("trainingPreparation", "Тренировка", R.drawable.lift_icon)
    data object Profile : Screen("profile", "Профиль", R.drawable.user_icon)
    data object Training: Screen("training", "Тренировка", null)
}