package com.sagnol.feature.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagnol.core.domain.usecase.InsertMealUseCase
import com.sagnol.mealmate.core.model.Meal
import com.sagnol.mealmate.feature.input.InputUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val insertMealUseCase: InsertMealUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(InputUiState())
    val uiState: StateFlow<InputUiState> = _uiState

    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun updateNutrient(
        calories: Int? = null, carbs: Int? = null, protein: Int? = null,
        fat: Int? = null, sugar: Int? = null, sodium: Int? = null
    ) {
        _uiState.update {
            it.copy(
                nutrient = it.nutrient.copy(
                    calories = calories ?: it.nutrient.calories,
                    carbs = carbs ?: it.nutrient.carbs,
                    protein = protein ?: it.nutrient.protein,
                    fat = fat ?: it.nutrient.fat,
                    sugar = sugar ?: it.nutrient.sugar,
                    sodium = sodium ?: it.nutrient.sodium
                )
            )
        }
    }

    fun updateTime(time: String) {
        _uiState.update { it.copy(time = time) }
    }

    fun updateDate(date: LocalDate) {
        _uiState.update { it.copy(date = date)}
    }

    fun saveMeal() {
        viewModelScope.launch {
            val state = _uiState.value
            val meal = Meal(
                title = state.title,
                nutrient = state.nutrient,
                time = state.time,
                date = state.date
            )
            insertMealUseCase.invoke(meal)
        }
    }

}