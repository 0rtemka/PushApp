package com.example.pushapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pushapp.feature.main.ui.MainScreen
import com.example.pushapp.feature.profile.ui.ProfileScreen
import com.example.pushapp.navigation.AppNavGraph
import com.example.pushapp.navigation.BottomNavBar
import com.example.pushapp.navigation.Screen
import com.example.pushapp.ui.theme.PushAppTheme
import dagger.hilt.android.AndroidEntryPoint

val routes = listOf(
    Screen.Home, Screen.TrainingPreparation, Screen.Calendar, Screen.Stats, Screen.Profile
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PushAppTheme(dynamicColor = false) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute !== Screen.Training.route)
                            BottomNavBar(
                                navController = navController,
                                items = routes
                            )
                    }, containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    AppNavGraph(
                        navController = navController,
                        modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenLightModeFullPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    navController = rememberNavController(),
                    items = routes
                )
            }
        ) { innerPadding ->
            MainScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenLightModeFullPreview() {
    PushAppTheme(darkTheme = false, dynamicColor = false) {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    navController = rememberNavController(),
                    items = routes
                )
            }
        ) { innerPadding ->
            ProfileScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenDarkModeFullPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    navController = rememberNavController(),
                    items = routes
                )
            }
        ) { innerPadding ->
            MainScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenDarkModeFullPreview() {
    PushAppTheme(darkTheme = true, dynamicColor = false) {
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    navController = rememberNavController(),
                    items = routes
                )
            }
        ) { innerPadding ->
            ProfileScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}