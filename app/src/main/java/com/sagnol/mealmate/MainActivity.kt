package com.sagnol.mealmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagnol.mealmate.core.ui.theme.MealMateTheme
import com.sagnol.mealmate.feature.setting.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val settingViewModel: SettingViewModel = hiltViewModel()

            val isDarkMode by settingViewModel.uiState
                .map { it.darkMode }
                .collectAsState(initial = false)

            MealMateTheme(darkTheme = isDarkMode) {
                MainScreen()
            }
        }
    }
}