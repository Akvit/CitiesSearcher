package com.example.citiessearcher.searcher.domain

import com.example.citiessearcher.searcher.domain.models.CityModel

interface CitiesRepository {

    suspend fun onCitiesRequest(): List<CityModel>
}