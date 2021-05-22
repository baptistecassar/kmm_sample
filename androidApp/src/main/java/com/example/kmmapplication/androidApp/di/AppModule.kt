package com.example.kmmapplication.androidApp.di

import com.example.kmmapplication.androidApp.ui.games.GamesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { GamesViewModel(get()) }
}
