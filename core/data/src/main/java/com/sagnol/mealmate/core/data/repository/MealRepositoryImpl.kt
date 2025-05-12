package com.sagnol.core.data.repository

import com.sagnol.core.data.database.MealDao
import com.sagnol.core.domain.repository.MealRepository
import com.sagnol.mealmate.core.data.mapper.toEntity
import com.sagnol.mealmate.core.data.mapper.toModel
import com.sagnol.mealmate.core.model.Meal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDao: MealDao
): MealRepository {
    override fun getMealsByDate(date: LocalDate): Flow<List<Meal>> =
        mealDao.getMealsByDate(date.toString()).map { list->
            list.map { it.toModel() }
        }

    override suspend fun insertMeal(meal: Meal) {
        mealDao.insertMeal(meal.toEntity())
    }

    override suspend fun deleteMeal(meal: Meal) {
        mealDao.deleteMeal(meal.toEntity())
    }

}