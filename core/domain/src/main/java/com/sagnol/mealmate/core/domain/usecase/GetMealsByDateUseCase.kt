package com.sagnol.mealmate.core.domain.usecase

import com.sagnol.mealmate.core.domain.repository.MealRepository
import com.sagnol.mealmate.core.model.Meal
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetMealsByDateUseCase @Inject constructor(
    private val repository: MealRepository
){
    operator fun invoke(date: String): Flow<List<Meal>> {
        return repository.getMealsByDate(date)
    }
}