package com.example.citiessearcher

import android.app.Application
import com.example.citiessearcher.searcher.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
    }
}