package com.sagnol.feature.input

import com.sagnol.mealmate.core.domain.usecase.InsertMealUseCase
import com.sagnol.mealmate.feature.input.InputViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class InputViewModelStateTest {

    private lateinit var viewModel: InputViewModel

    @Before
    fun setup() {
        val fakeRepository = FakeMealRepository()
        val useCase = InsertMealUseCase(fakeRepository)
        viewModel = InputViewModel(useCase)
    }

    @Test
    fun `updateTitle should update state`() {
        viewModel.updateTitle("닭가슴살")

        val state = viewModel.uiState.value
        assertEquals("닭가슴살", state.title)
    }

    @Test
    fun `updateDate should update state`() {
        viewModel.updateDate("2024-05-20")

        val state = viewModel.uiState.value
        assertEquals("2024-05-20", state.date)
    }

    @Test
    fun `updateTime should update state`() {
        viewModel.updateTime("저녁")

        val state = viewModel.uiState.value
        assertEquals("저녁", state.time)
    }

    @Test
    fun `updateNutrient should update nutrient fields`() {
        viewModel.updateNutrient(
            calories = 300,
            carbs = 30,
            protein = 20,
            fat = 10,
        )

        val nutrient = viewModel.uiState.value.nutrient
        assertEquals(300, nutrient.calories)
        assertEquals(30, nutrient.carbs)
        assertEquals(20, nutrient.protein)
        assertEquals(10, nutrient.fat)
    }
}
