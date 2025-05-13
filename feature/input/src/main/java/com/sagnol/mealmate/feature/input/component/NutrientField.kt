package com.sagnol.mealmate.feature.input.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun NutrientField(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
) {
    OutlinedTextField(
        value = value.toString(),
        onValueChange = {
            val intValue = it.toIntOrNull() ?: 0
            onValueChange(intValue)
        },
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth()
    )
}
