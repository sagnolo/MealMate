package com.sagnol.feature.setting

import app.cash.turbine.test
import com.sagnol.mealmate.core.domain.repository.SettingRepository
import com.sagnol.mealmate.feature.setting.SettingViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SettingViewModelTest {

    private lateinit var viewModel: SettingViewModel
    private lateinit var fakeRepository: SettingRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        fakeRepository = FakeSettingRepository()
        viewModel = SettingViewModel(fakeRepository)
    }

    @Test
    fun `updateState dark mode`() = runTest {
        viewModel.onDarkModeToggle(true)

        advanceUntilIdle()

        viewModel.uiState.test {
            val state = awaitItem()
            assertTrue(state.darkMode)
        }
    }
}