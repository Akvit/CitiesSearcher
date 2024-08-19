package com.example.citiessearcher.searcher.domain.dispatcherprovider

import kotlinx.coroutines.test.StandardTestDispatcher

class TestDispatcherProvider: DispatcherProvider {

    private val testDispatcher = StandardTestDispatcher()

    val scheduler = testDispatcher.scheduler

    override fun io() = testDispatcher

    override fun main() = testDispatcher

    override fun computation() = testDispatcher
}