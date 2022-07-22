package com.backbase.domain.di

import com.backbase.domain.interactors.DownloadCitiesInteractor
import com.backbase.domain.interactors.DownloadCitiesInteractorImpl
import com.backbase.domain.dispatcherprovider.CoroutineDispatcherProvider
import com.backbase.domain.dispatcherprovider.DispatcherProvider
import com.backbase.domain.usecases.FilterUseCase
import com.backbase.domain.usecases.FilterUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    single<DispatcherProvider> { CoroutineDispatcherProvider() }

    factory<FilterUseCase> { FilterUseCaseImpl(dispatcherProvider = get()) }

    factory <DownloadCitiesInteractor> {
        DownloadCitiesInteractorImpl(
            citiesRepository = get(),
            filterUseCase = get()
        )
    }

}