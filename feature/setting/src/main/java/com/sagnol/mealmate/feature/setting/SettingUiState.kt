package com.sagnol.mealmate.feature.setting

data class SettingUiState(
    val notificationsEnabled: Boolean = true,
    val defaultMealTime: String = "점심",
    val dailyCalorieTarget: Int = 2000,
    val darkMode: Boolean = false,
    val reminderTime: String = "08:00"
)