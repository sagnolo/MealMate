package com.sagnol.feature.input

import com.sagnol.mealmate.core.domain.usecase.InsertMealUseCase
import com.sagnol.mealmate.feature.input.InputViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class InputViewModelValidationTest {

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

    @Test
    fun `empty title`() = runTest {
        viewModel.updateDate("2024-05-20")
        viewModel.updateTime("점심")
        viewModel.updateNutrient(calories = 200)

        viewModel.saveMeal()
        advanceUntilIdle()

        assertTrue(fakeRepository.insertedMeals.isEmpty())
    }
}