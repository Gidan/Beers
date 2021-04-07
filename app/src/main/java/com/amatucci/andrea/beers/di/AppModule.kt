package com.amatucci.andrea.beers.di

import com.amatucci.andrea.beers.viewmodel.BeersViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { BeersViewModel(get()) }
}