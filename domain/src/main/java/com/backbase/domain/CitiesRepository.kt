package com.backbase.domain

import com.backbase.domain.models.CityModel

interface CitiesRepository {

    suspend fun onCitiesRequest(): List<CityModel>
}