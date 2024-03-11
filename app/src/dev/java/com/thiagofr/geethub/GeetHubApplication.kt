package com.thiagofr.geethub

import android.app.Application
import com.thiagofr.geethub.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GeetHubApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GeetHubApplication)
            modules(
                networkModule,
                mappersModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}