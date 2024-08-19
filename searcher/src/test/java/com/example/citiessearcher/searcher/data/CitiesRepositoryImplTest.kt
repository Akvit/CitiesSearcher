package com.example.citiessearcher.searcher.data

import com.example.citiessearcher.searcher.data.api.Api
import com.example.citiessearcher.searcher.domain.CitiesRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CitiesRepositoryImplTest {

    private lateinit var repository: CitiesRepository

    private val dispatcherProvider = StandardTestDispatcher()

    @Mock
    private lateinit var api: Api

    @Before
    fun setUp() {
        repository = CitiesRepositoryImpl(api)
    }

    @Test
    fun cachingTest() = runTest(dispatcherProvider.scheduler) {
        `when`(api.provideData()).thenReturn(unsortedEntities)
        repository.onCitiesRequest()
        repository.onCitiesRequest()
        Mockito.verify(api, Mockito.times(1)).provideData()
    }

    @Test
    fun unsortedEntitiesToSortedModels() = runTest(dispatcherProvider.scheduler){
        `when`(api.provideData()).thenReturn(unsortedEntities)
        val result = repository.onCitiesRequest()
        Assert.assertEquals(sortedModels, result)
    }
}