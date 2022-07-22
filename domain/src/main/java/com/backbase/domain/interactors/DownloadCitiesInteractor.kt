package com.backbase.domain.interactors

import com.backbase.domain.models.CityModel
import com.backbase.domain.models.ParameterForQuery

interface DownloadCitiesInteractor {

    suspend fun getCities(parameterForQuery: ParameterForQuery): List<CityModel>
}