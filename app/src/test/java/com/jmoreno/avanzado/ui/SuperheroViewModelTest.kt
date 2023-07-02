package com.jmoreno.avanzado.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jmoreno.avanzado.data.Repository
import com.jmoreno.avanzado.utils.generateLocations
import com.jmoreno.avanzado.utils.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class SuperheroViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // UUT o SUT
    private lateinit var viewModel: SuperheroViewModel

    // Mocks
    private lateinit var repository: Repository


    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mockk()
        viewModel = SuperheroViewModel(repository)
    }

    @Test
    fun `WHEN getLocations EXPECT successful response`()  {
        // GIVEN
        val locations = generateLocations(3, "101")
        coEvery { repository.getLocations("101")} returns locations

        // WHEN
        val actual = viewModel.getLocations("101")
        val actualLiveData = viewModel.locations.getOrAwaitValue()

        // THEN
        assert(actualLiveData.size == 3 && actualLiveData[0].hero.id == "101")
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
/*
En el test unitario real he tratado de testear la la llamada para recoger las localizaciones
que generamos en el mock del repositorio. Las generamos a partir del id del héroe y lo comprobamos
en el assert, así como el número de localizaciones
 */