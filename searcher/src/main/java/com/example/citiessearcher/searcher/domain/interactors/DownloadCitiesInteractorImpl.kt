package com.example.citiessearcher.searcher.domain.interactors

import com.example.citiessearcher.searcher.domain.CitiesRepository
import com.example.citiessearcher.searcher.domain.models.ParameterForQuery
import com.example.citiessearcher.searcher.domain.usecases.FilterUseCase

class DownloadCitiesInteractorImpl(
    private val citiesRepository: CitiesRepository,
    private val filterUseCase: FilterUseCase
) : DownloadCitiesInteractor {

    override suspend fun getCities(parameterForQuery: ParameterForQuery) = citiesRepository
        .onCitiesRequest()
            .run { filterUseCase.filter(this, parameterForQuery) }

}
