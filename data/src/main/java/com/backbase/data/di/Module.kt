package com.backbase.data.di

import com.backbase.data.CitiesRepositoryImpl
import com.backbase.data.api.Api
import com.backbase.data.api.LocalApi
import com.backbase.domain.CitiesRepository
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val dataModule = module {

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