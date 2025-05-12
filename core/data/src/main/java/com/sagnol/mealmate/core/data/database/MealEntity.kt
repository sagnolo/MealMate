package com.sagnol.core.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val calories: Int,
    val carbs: Int,
    val protein: Int,
    val fat: Int,
    val sugar: Int,
    val sodium: Int,
    val time: String,
    val date: String // LocalDate → String 저장
)