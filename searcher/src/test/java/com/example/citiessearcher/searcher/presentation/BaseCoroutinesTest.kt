package com.example.citiessearcher.searcher.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
open class BaseCoroutinesTest {

    private val dispatcherProvider = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcherProvider)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}