package com.sagnol.mealmate.core.data.mapper

import com.sagnol.mealmate.core.data.database.MealEntity
import com.sagnol.mealmate.core.model.Meal
import com.sagnol.mealmate.core.model.Nutrient
import java.time.LocalDate

fun MealEntity.toModel(): Meal = Meal(
    id = id,
    title = title,
    nutrient = Nutrient(
        calories = calories,
        carbs = carbs,
        protein = protein,
        fat = fat,
        sugar = sugar,
        sodium = sodium
    ),
    time = time,
    date = date
)

fun Meal.toEntity(): MealEntity = MealEntity(
    id = id,
    title = title,
    calories = nutrient.calories,
    carbs = nutrient.carbs,
    protein = nutrient.protein,
    fat = nutrient.fat,
    sugar = nutrient.sugar,
    sodium = nutrient.sodium,
    time = time,
    date = date
)
