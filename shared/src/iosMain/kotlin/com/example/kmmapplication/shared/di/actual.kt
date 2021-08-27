package com.example.kmmapplication.shared.di

import co.touchlab.kermit.Logger
import co.touchlab.kermit.NSLogLogger
import org.koin.dsl.module

actual fun platformModule() = module {
    single<Logger> { NSLogLogger() }
}
