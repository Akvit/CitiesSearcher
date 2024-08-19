package com.example.citiessearcher.searcher.domain.usecases

import com.example.citiessearcher.searcher.domain.models.CityModel
import com.example.citiessearcher.searcher.domain.models.ParameterForQuery

interface FilterUseCase {

    suspend fun filter(rawList: List<CityModel>, parameterForQuery: ParameterForQuery) : List<CityModel>
}