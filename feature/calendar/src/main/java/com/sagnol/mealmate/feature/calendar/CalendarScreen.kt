package com.sagnol.mealmate.feature.calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel()
) {
    val meals by viewModel.meals.collectAsState()
    val selectedDate by viewModel.date.collectAsState()
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(datePickerState.selectedDateMillis) {
        datePickerState.selectedDateMillis?.let { millis ->
            val newDate = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            viewModel.updateDate(newDate)
            showDatePicker = false
        }
    }

    Column(Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("📅 $selectedDate", style = MaterialTheme.typography.titleLarge)
            IconButton(onClick = { showDatePicker = !showDatePicker }) {
                Icon(Icons.Default.DateRange, contentDescription = "날짜 선택")
            }
        }

        if (showDatePicker) {
            Popup(
                alignment = Alignment.TopCenter,
                onDismissRequest = { showDatePicker = false }
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (meals.isEmpty()) {
            Text("이 날의 식단이 없습니다.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(meals) { meal ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(meal.title, style = MaterialTheme.typography.titleMedium)
                            Text("시간: ${meal.time}")
                            Text("칼로리: ${meal.nutrient.calories} kcal")
                            Text("탄수화물: ${meal.nutrient.carbs} g")
                            Text("단백질: ${meal.nutrient.protein} g")
                            Text("지방: ${meal.nutrient.fat} g")
                            Text("당: ${meal.nutrient.sugar} g")
                            Text("나트륨: ${meal.nutrient.sodium} g")
                        }
                    }
                }
            }
        }
    }
}