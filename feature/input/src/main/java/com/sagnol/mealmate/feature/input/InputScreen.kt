package com.sagnol.feature.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sagnol.mealmate.feature.input.component.DatePickerField
import com.sagnol.mealmate.feature.input.component.NutrientField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(
    viewModel: InputViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("식단 입력", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = uiState.title,
            onValueChange = viewModel::updateTitle,
            label = { Text("음식 이름")},
            modifier = Modifier.fillMaxWidth()
        )

        DatePickerField(
            selectedDate = uiState.date,
            onDateSelected = viewModel::updateDate,
            modifier = Modifier.fillMaxWidth()
        )

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
                modifier = Modifier.menuAnchor().fillMaxWidth()
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
        NutrientField("당 (g)", uiState.nutrient.sugar) {
            viewModel.updateNutrient(sugar = it)
        }
        NutrientField("나트륨 (mg)", uiState.nutrient.sodium) {
            viewModel.updateNutrient(sodium = it)
        }

        Button(
            onClick = viewModel::saveMeal,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("저장하기")
        }
    }
}