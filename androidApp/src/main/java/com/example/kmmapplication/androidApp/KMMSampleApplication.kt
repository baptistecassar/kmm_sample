package com.example.kmmapplication.androidApp

import android.app.Application
import co.touchlab.kermit.Kermit
import com.example.kmmapplication.androidApp.di.appModule
import com.example.kmmapplication.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KMMSampleApplication : Application(), KoinComponent {
    private val logger: Kermit by inject()

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@KMMSampleApplication)
            modules(appModule)
        }

        logger.d { "KMMSampleApplication" }
    }
}