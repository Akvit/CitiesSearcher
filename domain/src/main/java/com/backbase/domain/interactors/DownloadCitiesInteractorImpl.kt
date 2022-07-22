package com.backbase.domain.interactors

import com.backbase.domain.CitiesRepository
import com.backbase.domain.models.ParameterForQuery
import com.backbase.domain.usecases.FilterUseCase

class DownloadCitiesInteractorImpl(
    private val citiesRepository: CitiesRepository,
    private val filterUseCase: FilterUseCase
) : DownloadCitiesInteractor {

    override suspend fun getCities(parameterForQuery: ParameterForQuery) = citiesRepository
        .onCitiesRequest()
            .run { filterUseCase.filter(this, parameterForQuery) }

}
