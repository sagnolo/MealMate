package com.sagnol.mealmate.feature.input

import com.sagnol.mealmate.core.model.Nutrient
import java.time.LocalDate

data class InputUiState(
    val title: String = "",
    val nutrient: Nutrient = Nutrient(),
    val time: String = "아침",
    val date: String = "",
)