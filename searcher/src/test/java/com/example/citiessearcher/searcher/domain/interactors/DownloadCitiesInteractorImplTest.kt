package com.example.citiessearcher.searcher.domain.interactors

import com.example.citiessearcher.searcher.domain.dispatcherprovider.TestDispatcherProvider
import com.example.citiessearcher.searcher.domain.sortedModels
import com.example.citiessearcher.searcher.domain.CitiesRepository
import com.example.citiessearcher.searcher.domain.models.ParameterForQuery
import com.example.citiessearcher.searcher.domain.usecases.FilterUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DownloadCitiesInteractorImplTest {

    private lateinit var interactor: DownloadCitiesInteractor

    private val dispatcherProvider = TestDispatcherProvider()

    @Mock
    private lateinit var citiesRepository: CitiesRepository

    @Mock
    private lateinit var filterUseCase: FilterUseCase

    @Before
    fun setUp() {
        interactor = DownloadCitiesInteractorImpl(citiesRepository, filterUseCase)
    }

    @Test
    fun getCitiesTest() = runTest(dispatcherProvider.scheduler) {
        `when`(citiesRepository.onCitiesRequest()).thenReturn(sortedModels)
        `when`(filterUseCase.filter(sortedModels, ParameterForQuery())).thenReturn(sortedModels)

        interactor.getCities(ParameterForQuery())

        verify(citiesRepository).onCitiesRequest()
        verify(filterUseCase).filter(sortedModels, ParameterForQuery())
    }
}