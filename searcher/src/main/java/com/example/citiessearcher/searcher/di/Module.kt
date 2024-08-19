package com.example.citiessearcher.searcher.di

import com.example.citiessearcher.searcher.data.CitiesRepositoryImpl
import com.example.citiessearcher.searcher.data.api.Api
import com.example.citiessearcher.searcher.data.api.LocalApi
import com.example.citiessearcher.searcher.domain.CitiesRepository
import com.example.citiessearcher.searcher.domain.dispatcherprovider.CoroutineDispatcherProvider
import com.example.citiessearcher.searcher.domain.dispatcherprovider.DispatcherProvider
import com.example.citiessearcher.searcher.domain.interactors.DownloadCitiesInteractor
import com.example.citiessearcher.searcher.domain.interactors.DownloadCitiesInteractorImpl
import com.example.citiessearcher.searcher.domain.usecases.FilterUseCase
import com.example.citiessearcher.searcher.domain.usecases.FilterUseCaseImpl
import com.example.citiessearcher.searcher.presentation.CitiesViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    viewModel{ CitiesViewModel(interactor = get()) }

    single<DispatcherProvider> { CoroutineDispatcherProvider() }

    factory<FilterUseCase> {
        FilterUseCaseImpl(
            dispatcherProvider = get()
        )
    }

    factory <DownloadCitiesInteractor> {
        DownloadCitiesInteractorImpl(
            citiesRepository = get(),
            filterUseCase = get()
        )
    }
    single<CitiesRepository> {
        CitiesRepositoryImpl(
            api = get()
        )
    }

    factory<Api> {
        LocalApi(
            context = get(),
            gson = get(),
            dispatcherProvider = get()
        )
    }

    single { GsonBuilder().create() }

}