package com.backbase.citiessearcher

import android.app.Application
import com.backbase.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(domainModule)
        }
    }
}