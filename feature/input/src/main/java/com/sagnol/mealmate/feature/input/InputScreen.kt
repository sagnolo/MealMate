package com.sagnol.mealmate.feature.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sagnol.mealmate.feature.input.component.NutrientField
import kotlinx.coroutines.flow.collectLatest
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    navController: NavController,
    viewModel: InputViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let { millis ->
        val date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
        date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    } ?: "날짜를 선택하세요"

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest { event ->
            when (event) {
                is InputUiEvent.SaveSuccess -> {
                    navController.navigate("calendar") {
                        popUpTo("input") { inclusive = true }
                    }
                }
            }
        }
    }

    LaunchedEffect(selectedDate) {
        selectedDate.let {
            viewModel.updateDate(it)
            showDatePicker = false
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("식단 입력", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = uiState.title,
            onValueChange = viewModel::updateTitle,
            label = { Text("음식 이름")},
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text("날짜") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        if(showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopCenter
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                )
            }
        }

        val options = listOf("아침", "점심", "저녁")
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = uiState.time,
                onValueChange = {},
                readOnly = true,
                label = { Text("식사 시간대") },
                modifier = Modifier.fillMaxWidth().menuAnchor(MenuAnchorType.PrimaryEditable, enabled = true)
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            viewModel.updateTime(option)
                            expanded = false
                        }
                    )
                }
            }
        }

        // 영양소 입력 필드 반복 렌더링
        NutrientField("칼로리 (kcal)", uiState.nutrient.calories) {
            viewModel.updateNutrient(calories = it)
        }
        NutrientField("탄수화물 (g)", uiState.nutrient.carbs) {
            viewModel.updateNutrient(carbs = it)
        }
        NutrientField("단백질 (g)", uiState.nutrient.protein) {
            viewModel.updateNutrient(protein = it)
        }
        NutrientField("지방 (g)", uiState.nutrient.fat) {
            viewModel.updateNutrient(fat = it)
        }

        Button(
            onClick = viewModel::saveMeal,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("저장하기")
        }
    }
}