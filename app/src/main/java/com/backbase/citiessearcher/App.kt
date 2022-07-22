package com.backbase.citiessearcher

import android.app.Application
import com.backbase.data.di.dataModule
import com.backbase.domain.di.domainModule
import com.backbase.presentation.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(dataModule, domainModule, presentationModule)
        }
    }
}