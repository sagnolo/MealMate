package com.sagnol.mealmate

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sagnol.mealmate.feature.calendar.CalendarScreen
import com.sagnol.mealmate.feature.input.InputScreen

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "input"
    ) {
        composable("input") {
            InputScreen(navController = navController)
        }
        composable("calendar") {
            CalendarScreen()
        }
    }
}