package com.sagnol.mealmate.feature.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagnol.mealmate.core.domain.usecase.GetMealsByDateUseCase
import com.sagnol.mealmate.core.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val getMealsByDateUseCase: GetMealsByDateUseCase
): ViewModel() {

    private val _date = MutableStateFlow(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
    private val _meals = MutableStateFlow<List<Meal>>(emptyList())

    val date: StateFlow<String> = _date
    val meals: StateFlow<List<Meal>> = _meals

    init {
        fetchMeals()
    }

    fun updateDate(newDate: String) {
        _date.value = newDate
    }

    private fun fetchMeals() {
        viewModelScope.launch {
            _date.flatMapLatest { date ->
                getMealsByDateUseCase(date)
            }.collect { mealList ->
                _meals.value = mealList
            }
        }
    }

}