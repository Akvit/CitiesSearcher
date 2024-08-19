package com.example.citiessearcher.searcher.data

import com.example.citiessearcher.searcher.data.api.Api
import com.example.citiessearcher.searcher.domain.CitiesRepository
import com.example.citiessearcher.searcher.domain.models.CityModel

class CitiesRepositoryImpl(private val api: Api) : CitiesRepository {

    private var sortedList = emptyList<CityModel>()

    override suspend fun onCitiesRequest() = sortedList.ifEmpty {
            api.provideData()
                .map { it.toModel() }
                .sortedBy { it.sortedKey }
        }.also { sortedList = it }

}