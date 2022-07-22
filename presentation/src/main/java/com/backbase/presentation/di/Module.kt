package com.backbase.presentation.di

import com.backbase.presentation.CitiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel{ CitiesViewModel(interactor = get()) }
}