package com.sagnol.mealmate.feature.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagnol.mealmate.core.domain.repository.SettingRepository
import com.sagnol.mealmate.core.model.UserSetting
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: SettingRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(SettingUiState())
    val uiState: StateFlow<SettingUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getSettings().collect { setting ->
                _uiState.value = SettingUiState(
                    notificationsEnabled = setting.notificationsEnabled,
                    defaultMealTime = setting.defaultMealTime,
                    dailyCalorieTarget = setting.dailyCalorieTarget,
                    darkMode = setting.darkMode
                )
            }
        }
    }

    fun onDarkModeToggle(enabled: Boolean) {
        viewModelScope.launch {
            repository.updateDarkMode(enabled)
        }
    }

    fun onNotificationToggle(enabled: Boolean) {
        viewModelScope.launch {
            repository.updateNotifications(enabled)
        }
    }

    fun onTimeChange(time: String) {
        viewModelScope.launch {
            repository.updateDefaultMealTime(time)
        }
    }

    fun onCalorieChange(calories: Int) {
        viewModelScope.launch {
            repository.updateCalorieTarget(calories)
        }
    }
}