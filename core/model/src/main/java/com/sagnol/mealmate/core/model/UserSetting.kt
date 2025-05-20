package com.sagnol.mealmate.core.model

data class UserSetting(
    val darkMode: Boolean = false,
    val notificationsEnabled: Boolean = true,
    val defaultMealTime: String = "점심",
    val dailyCalorieTarget: Int = 2000,
    val reminderTime: String = "08:00"
)