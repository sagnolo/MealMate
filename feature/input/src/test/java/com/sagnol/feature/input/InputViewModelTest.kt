package com.sagnol.feature.input

import com.sagnol.mealmate.core.domain.usecase.InsertMealUseCase
import com.sagnol.mealmate.feature.input.InputViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class InputViewModelTest {

    private lateinit var viewModel: InputViewModel
    private lateinit var fakeRepository: FakeMealRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeMealRepository()
        val useCase = InsertMealUseCase(fakeRepository)
        viewModel = InputViewModel(useCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `insertMeal in repository`() = runTest {
        viewModel.updateTitle("계란후라이")
        viewModel.updateDate("2024-05-19")
        viewModel.updateTime("아침")
        viewModel.updateNutrient(calories = 200, carbs = 0, protein = 10, fat = 15)

        // when
        viewModel.saveMeal()
        advanceUntilIdle()

        // then
        val saved = fakeRepository.insertedMeals.firstOrNull()
        assertNotNull(saved)
        assertEquals("계란후라이", saved?.title)
        assertEquals("2024-05-19", saved?.date)
        assertEquals("아침", saved?.time)
        assertEquals(200, saved?.nutrient?.calories)
        assertEquals(10, saved?.nutrient?.protein)
    }
}