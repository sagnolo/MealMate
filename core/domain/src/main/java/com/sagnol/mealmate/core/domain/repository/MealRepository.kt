package com.sagnol.core.domain.repository

import com.sagnol.mealmate.core.model.Meal
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface MealRepository {
    fun getMealsByDate(date: LocalDate): Flow<List<Meal>>
    suspend fun insertMeal(meal: Meal)
    suspend fun deleteMeal(meal: Meal)
}