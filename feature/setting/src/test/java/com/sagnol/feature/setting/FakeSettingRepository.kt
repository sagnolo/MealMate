package com.sagnol.feature.setting

import com.sagnol.mealmate.core.domain.repository.SettingRepository
import com.sagnol.mealmate.core.model.UserSetting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

class FakeSettingRepository: SettingRepository {

    private val _setting = MutableStateFlow(UserSetting())
    override fun getSettings(): Flow<UserSetting> = _setting

    override suspend fun updateDarkMode(enabled: Boolean) {
        _setting.value = _setting.value.copy(darkMode = enabled)
    }

    override suspend fun updateNotifications(enabled: Boolean) {
    }

    override suspend fun updateDefaultMealTime(time: String) {
    }

    override suspend fun updateCalorieTarget(calories: Int) {
    }

    override suspend fun updateReminderTime(time: String) {
    }
}