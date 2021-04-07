package com.amatucci.andrea.beers

import android.app.Application
import com.amatucci.andrea.beers.di.appModule
import com.amatucci.andrea.beers.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BeersApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BeersApplication)
            modules(networkModule, appModule)
        }
    }

}