package com.sagnol.feature.input

import com.sagnol.mealmate.core.domain.repository.MealRepository
import com.sagnol.mealmate.core.model.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeMealRepository: MealRepository {

    val insertedMeals = mutableListOf<Meal>()

    override fun getMealsByDate(date: String): Flow<List<Meal>> = flowOf()

    override suspend fun insertMeal(meal: Meal) {
        insertedMeals.add(meal)
    }

    override suspend fun deleteMeal(meal: Meal){}
}