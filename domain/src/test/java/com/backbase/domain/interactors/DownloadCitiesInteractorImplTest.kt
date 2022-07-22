package com.backbase.domain.interactors

import com.backbase.domain.CitiesRepository
import com.backbase.domain.dispatcherprovider.TestDispatcherProvider
import com.backbase.domain.models.ParameterForQuery
import com.backbase.domain.sortedModels
import com.backbase.domain.usecases.FilterUseCase
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