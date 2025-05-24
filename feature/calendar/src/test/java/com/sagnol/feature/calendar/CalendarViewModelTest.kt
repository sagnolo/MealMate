package com.sagnol.feature.calendar

import com.sagnol.mealmate.core.domain.usecase.GetMealsByDateUseCase
import com.sagnol.mealmate.core.model.Meal
import com.sagnol.mealmate.core.model.Nutrient
import com.sagnol.mealmate.feature.calendar.CalendarViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CalendarViewModelTest {

    private lateinit var viewModel: CalendarViewModel
    private lateinit var fakeRepository: FakeMealRepository

    @Before
    fun setup() {
        fakeRepository = FakeMealRepository()
        val useCase = GetMealsByDateUseCase(fakeRepository)
        viewModel = CalendarViewModel(useCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `loadMeals`() = runTest {
        val date = "2025-05-22"
        val meal = Meal(
            id = 1,
            title = "샐러드",
            date = date,
            time = "점심",
            nutrient = Nutrient(calories = 250, carbs = 20, protein = 15, fat = 8)
        )
        fakeRepository.addMealForDate(date, meal)

        viewModel.updateDate(date)
        advanceUntilIdle()

        val meals = viewModel.meals.value
        assertEquals(1, meals.size)
        assertEquals("김밥", meals[0].title)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `updateDate return empty list`() = runTest {
        viewModel.updateDate("2024-01-01")
        advanceUntilIdle()

        val meals = viewModel.meals.value
        assertTrue(meals.isEmpty())
    }
}