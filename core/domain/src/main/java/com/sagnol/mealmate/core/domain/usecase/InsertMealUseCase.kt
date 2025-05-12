package com.sagnol.core.domain.usecase

import com.sagnol.core.domain.repository.MealRepository
import com.sagnol.mealmate.core.model.Meal
import javax.inject.Inject

class InsertMealUseCase @Inject constructor(
    private val repository: MealRepository
){
    suspend operator fun invoke(meal: Meal) = repository.insertMeal(meal)
}