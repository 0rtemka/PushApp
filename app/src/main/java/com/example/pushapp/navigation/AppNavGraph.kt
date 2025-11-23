package com.example.pushapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pushapp.feature.main.ui.MainScreen
import com.example.pushapp.feature.profile.ui.ProfileScreen
import com.example.pushapp.feature.profile.ui.StatsScreen
import com.example.pushapp.feature.trainingPreparation.ui.TrainingPreparationScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            MainScreen()
        }

        composable(Screen.Training.route) {
            TrainingPreparationScreen()
        }

        composable(Screen.Stats.route) {
            StatsScreen()
        }

        composable(Screen.Profile.route) {
            ProfileScreen()
        }
    }
}