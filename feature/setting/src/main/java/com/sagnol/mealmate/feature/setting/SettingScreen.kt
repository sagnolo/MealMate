package com.sagnol.mealmate.feature.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(
    viewModel: SettingViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("앱 설정", style = MaterialTheme.typography.headlineSmall)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("다크 모드", modifier = Modifier.weight(1f))
            Switch(
                checked = state.darkMode,
                onCheckedChange = viewModel::onDarkModeToggle
            )
        }

        // 알림 설정
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("식사 알림", modifier = Modifier.weight(1f))
            Switch(
                checked = state.notificationsEnabled,
                onCheckedChange = viewModel::onNotificationToggle
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 기본 식사 시간 설정
        var expanded by remember { mutableStateOf(false) }
        val options = listOf("아침", "점심", "저녁")

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                readOnly = true,
                value = state.defaultMealTime,
                onValueChange = {},
                label = { Text("기본 식사 시간대") },
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true),
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "선택")
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            viewModel.onTimeChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 칼로리 목표 입력
        OutlinedTextField(
            value = state.dailyCalorieTarget.toString(),
            onValueChange = {
                val number = it.toIntOrNull() ?: 0
                viewModel.onCalorieChange(number)
            },
            label = { Text("일일 칼로리 목표 (kcal)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
