package com.example.pushapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pushapp.feature.main.ui.MainScreen
import com.example.pushapp.feature.profile.ui.ProfileScreen
import com.example.pushapp.feature.stats.ui.StatsScreen
import com.example.pushapp.feature.training.ui.TrainingHostScreen
import com.example.pushapp.feature.trainingPreparation.ui.TrainingPreparationScreen

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            MainScreen(onStartTrainingClick = { navController.navigate(Screen.TrainingPreparation.route) })
        }

        composable(Screen.TrainingPreparation.route) {
            TrainingPreparationScreen(
                onBackClick = { navController.popBackStack() },
                onStartTrainingClick = { navController.navigate(Screen.Training.route) })
        }

        composable(Screen.Stats.route) {
            StatsScreen(onBackClick = { navController.popBackStack() })
        }

        composable(Screen.Profile.route) {
            ProfileScreen(onBackClick = { navController.popBackStack() })
        }

        composable(Screen.Training.route) {
            TrainingHostScreen(onReturnHome = { navController.navigate(Screen.Home.route) })
        }
    }
}