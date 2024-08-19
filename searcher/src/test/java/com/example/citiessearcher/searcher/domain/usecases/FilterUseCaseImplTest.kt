package com.example.citiessearcher.searcher.domain.usecases

import com.example.citiessearcher.searcher.domain.dispatcherprovider.TestDispatcherProvider
import com.example.citiessearcher.searcher.domain.onEnterC
import com.example.citiessearcher.searcher.domain.onEnterDi
import com.example.citiessearcher.searcher.domain.otherModels
import com.example.citiessearcher.searcher.domain.sortedModels
import com.example.citiessearcher.searcher.domain.models.CityModel
import com.example.citiessearcher.searcher.domain.models.ParameterForQuery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class FilterUseCaseImplTest {

    private lateinit var filterUseCase: FilterUseCase

    private val dispatcherProvider = TestDispatcherProvider()

    @Before
    fun setUp() {
        filterUseCase = FilterUseCaseImpl(dispatcherProvider)
    }

    @Test
    fun `on empty query`() = runTest(dispatcherProvider.scheduler) {
        val result = filterUseCase.filter(sortedModels, ParameterForQuery())
        assertEquals(result, sortedModels)
        assertNotEquals(result, otherModels)
    }

    @Test
    fun `on enter symbol c`() = runTest(dispatcherProvider.scheduler) {
        val result = filterUseCase.filter(sortedModels, ParameterForQuery("c"))
        assertEquals(result, onEnterC)
    }

    @Test
    fun `on enter symbols Di`() = runTest(dispatcherProvider.scheduler) {
        val result = filterUseCase.filter(sortedModels, ParameterForQuery("Di"))
        assertEquals(result, onEnterDi)
    }

    @Test
    fun `on enter random symbols`() = runTest(dispatcherProvider.scheduler) {
        val result = filterUseCase.filter(sortedModels, ParameterForQuery("lalalala"))
        assertEquals(result, emptyList<CityModel>())
    }

}