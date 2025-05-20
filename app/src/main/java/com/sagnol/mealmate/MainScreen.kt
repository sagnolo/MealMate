package com.sagnol.mealmate

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sagnol.mealmate.feature.calendar.CalendarScreen
import com.sagnol.mealmate.feature.input.InputScreen
import com.sagnol.mealmate.feature.setting.SettingScreen
import com.sagnol.mealmate.navigation.Screen

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Input.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Input.route) { InputScreen(navController) }
            composable(Screen.Calendar.route) { CalendarScreen() }
            composable(Screen.Setting.route) { SettingScreen() }
        }
    }
}