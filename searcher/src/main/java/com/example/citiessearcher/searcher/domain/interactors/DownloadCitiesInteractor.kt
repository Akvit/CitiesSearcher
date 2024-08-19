package com.example.citiessearcher.searcher.domain.interactors

import com.example.citiessearcher.searcher.domain.models.CityModel
import com.example.citiessearcher.searcher.domain.models.ParameterForQuery

interface DownloadCitiesInteractor {

    suspend fun getCities(parameterForQuery: ParameterForQuery): List<CityModel>
}