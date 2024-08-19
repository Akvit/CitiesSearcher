package com.example.citiessearcher.searcher.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.citiessearcher.searcher.domain.interactors.DownloadCitiesInteractor
import com.example.citiessearcher.searcher.domain.models.ParameterForQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*
import org.mockito.kotlin.argumentCaptor

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CitiesViewModelTest: BaseCoroutinesTest() {

    private lateinit var viewModel: CitiesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var interactor: DownloadCitiesInteractor

    @Before
    fun setUp() {
        viewModel = CitiesViewModel(interactor)
    }

    @Test
    fun testLoadingDataWithParticularQuery() = runTest {
        val someQuery = "some query"
        val captor = argumentCaptor<ParameterForQuery>()
        `when`(interactor.getCities(captor.capture())).thenReturn(sortedModels)
        viewModel.startLoading(someQuery)
        delay(DEFAULT_DELAY)
        val captured = captor.firstValue.query
        val result = viewModel.citiesLiveData.getOrAwaitValue()
        Assert.assertEquals(result, sortedModels)
        Assert.assertEquals(captured, someQuery)
    }

    @Test(expected = Throwable::class)
    fun onException() = runTest {
        `when`(interactor.getCities(ParameterForQuery())).thenThrow(Throwable("some error"))
        viewModel.startLoading()
        delay(DEFAULT_DELAY)
        val result = viewModel.error.getOrAwaitValue()
        Assert.assertEquals(result, "some error")
    }

    companion object {
        private const val DEFAULT_DELAY = 100L
    }
}

