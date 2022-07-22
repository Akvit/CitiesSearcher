package com.backbase.data

import com.backbase.data.api.Api
import com.backbase.domain.CitiesRepository
import com.backbase.domain.models.CityModel

class CitiesRepositoryImpl(private val api: Api) : CitiesRepository {

    private var sortedList = emptyList<CityModel>()

    override suspend fun onCitiesRequest() = sortedList.ifEmpty {
            api.provideData()
                .map { it.toModel() }
                .sortedBy { it.sortedKey }
        }.also { sortedList = it }

}