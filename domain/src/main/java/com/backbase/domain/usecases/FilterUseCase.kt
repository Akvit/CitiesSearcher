package com.backbase.domain.usecases

import com.backbase.domain.models.CityModel
import com.backbase.domain.models.ParameterForQuery

interface FilterUseCase {

    suspend fun filter(rawList: List<CityModel>, parameterForQuery: ParameterForQuery) : List<CityModel>
}