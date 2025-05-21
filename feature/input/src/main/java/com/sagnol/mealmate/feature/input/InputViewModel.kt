package com.sagnol.mealmate.feature.input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagnol.mealmate.core.domain.usecase.InsertMealUseCase
import com.sagnol.mealmate.core.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InputViewModel @Inject constructor(
    private val insertMealUseCase: InsertMealUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(InputUiState())
    val uiState: StateFlow<InputUiState> = _uiState

    private val _event = MutableSharedFlow<InputUiEvent>()
    val event = _event.asSharedFlow()

    fun updateTitle(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    fun updateNutrient(
        calories: Int? = null, carbs: Int? = null, protein: Int? = null,
        fat: Int? = null
    ) {
        _uiState.update {
            it.copy(
                nutrient = it.nutrient.copy(
                    calories = calories ?: it.nutrient.calories,
                    carbs = carbs ?: it.nutrient.carbs,
                    protein = protein ?: it.nutrient.protein,
                    fat = fat ?: it.nutrient.fat,
                )
            )
        }
    }

    fun updateTime(time: String) {
        _uiState.update { it.copy(time = time) }
    }

    fun updateDate(date: String) {
        _uiState.update { it.copy(date = date)}
    }

    fun saveMeal() {
        val state = _uiState.value
        if (state.title.isBlank() || state.date.isBlank() || state.time.isBlank()) return

        viewModelScope.launch {
            val state = _uiState.value
            val meal = Meal(
                title = state.title,
                nutrient = state.nutrient,
                time = state.time,
                date = state.date
            )
            insertMealUseCase(meal)
            _event.emit(InputUiEvent.SaveSuccess)
        }
    }

}