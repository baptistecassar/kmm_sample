package com.example.kmmapplication.androidApp

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.kmmapplication.androidApp.di.appModule
import com.example.kmmapplication.shared.di.initKoin
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.test.check.checkModules
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TestKoinGraph  {
    private val context = getApplicationContext<Context>()

    @Test
    fun `checking koin modules`() {
        initKoin {
            androidContext(context)
            modules(appModule)
        }.checkModules()
    }
}