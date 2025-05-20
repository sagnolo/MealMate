package com.sagnol.mealmate.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sagnol.mealmate.core.domain.repository.SettingRepository
import com.sagnol.mealmate.core.model.UserSetting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): SettingRepository {

    override fun getSettings(): Flow<UserSetting> = dataStore.data
        .map { prefs ->
            UserSetting(
                notificationsEnabled = prefs[KEY_NOTIFICATIONS] ?: true,
                defaultMealTime = prefs[KEY_DEFAULT_MEAL_TIME] ?: "점심",
                dailyCalorieTarget = prefs[KEY_CALORIE_TARGET] ?: 2000,
                reminderTime = prefs[KEY_REMINDER_TIME] ?: "08:00",
                darkMode = prefs[KEY_DARK_MODE] ?: false,
            )
        }

    override suspend fun updateDarkMode(enabled: Boolean) {
        dataStore.edit {it[KEY_DARK_MODE] = enabled}
    }

    override suspend fun updateCalorieTarget(calories: Int) {
        dataStore.edit { it[KEY_CALORIE_TARGET] = calories }
    }

    override suspend fun updateNotifications(enabled: Boolean) {
        dataStore.edit { it[KEY_NOTIFICATIONS] = enabled }
    }

    override suspend fun updateDefaultMealTime(time: String) {
        dataStore.edit { it[KEY_DEFAULT_MEAL_TIME] = time}
    }

    override suspend fun updateReminderTime(time: String) {
        dataStore.edit { it[KEY_REMINDER_TIME] = time}
    }

    companion object {
        private val KEY_NOTIFICATIONS = booleanPreferencesKey("notifications")
        private val KEY_DEFAULT_MEAL_TIME = stringPreferencesKey("default_meal_time")
        private val KEY_CALORIE_TARGET = intPreferencesKey("calorie_target")
        private val KEY_REMINDER_TIME = stringPreferencesKey("reminder_time")
        private val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
    }

}