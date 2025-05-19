package com.sagnol.mealmate.core.domain.usecase

import com.sagnol.mealmate.core.domain.repository.MealRepository
import com.sagnol.mealmate.core.model.Meal
import javax.inject.Inject

class DeleteMealUseCase @Inject constructor(
    private val repository: MealRepository
) {
    suspend operator fun invoke(meal: Meal) = repository.deleteMeal(meal)
}