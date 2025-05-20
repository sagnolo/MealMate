package com.sagnol.mealmate.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Input : Screen("input", "Input", Icons.Default.Edit)
    object Calendar : Screen("calendar", "Calendar", Icons.Default.DateRange)
    object Setting : Screen("setting", "Setting", Icons.Default.Settings)

    companion object {
        val bottomNavItems = listOf(Input, Calendar, Setting)
    }
}