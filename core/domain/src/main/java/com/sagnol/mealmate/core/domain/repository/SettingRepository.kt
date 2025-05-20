package com.sagnol.mealmate.core.domain.repository

import com.sagnol.mealmate.core.model.UserSetting
import kotlinx.coroutines.flow.Flow

interface SettingRepository {
    fun getSettings(): Flow<UserSetting>
    suspend fun updateDarkMode(enabled: Boolean)
    suspend fun updateNotifications(enabled: Boolean)
    suspend fun updateDefaultMealTime(time: String)
    suspend fun updateCalorieTarget(calories: Int)
    suspend fun updateReminderTime(time: String)
}