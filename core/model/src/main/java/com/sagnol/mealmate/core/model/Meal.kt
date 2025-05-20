package com.sagnol.mealmate.core.model

data class Meal(
    val id: Long = 0L,
    val title: String,
    val nutrient: Nutrient,
    val time: String, // 아침, 점심, 저녁
    val date: String
)
