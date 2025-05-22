package com.sagnol.feature.calendar

import com.sagnol.mealmate.core.domain.repository.MealRepository
import com.sagnol.mealmate.core.model.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMealRepository: MealRepository {


    private val mealMap = mutableMapOf<String, List<Meal>>()

    override fun getMealsByDate(date: String): Flow<List<Meal>> {
        return flowOf(mealMap[date] ?: emptyList())
    }

    fun addMealForDate(date: String, meal: Meal) {
        mealMap[date] = listOf(meal)
    }

    override suspend fun deleteMeal(meal: Meal) {}

    override suspend fun insertMeal(meal: Meal) {}
}