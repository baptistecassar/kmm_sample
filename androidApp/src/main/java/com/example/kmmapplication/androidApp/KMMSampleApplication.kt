package com.example.kmmapplication.androidApp

import android.app.Application
import com.example.kmmapplication.androidApp.di.appModule
import com.example.kmmapplication.shared.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.KoinAppDeclaration

class KMMSampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@KMMSampleApplication)
            modules(appModule)
        }
    }
}