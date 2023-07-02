package com.jmoreno.avanzado.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jmoreno.avanzado.data.FakeRepository
import com.jmoreno.avanzado.data.Repository
import com.jmoreno.avanzado.utils.generateCredentials
import com.jmoreno.avanzado.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class LoginViewModelTestWithFakeRepository {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: LoginViewModel

    // Mocks
    private lateinit var repository: Repository


    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = FakeRepository()
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun `WHEN doLogin EXPECT the token to continue with the app`() = runTest  {
        // GIVEN
        val credentials = generateCredentials("javier","moreno")

        // WHEN
        val actual = viewModel.getLogin(credentials)
        val actualLiveData = viewModel.token.getOrAwaitValue()

        // THEN
        assert(actualLiveData.length == 10)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}