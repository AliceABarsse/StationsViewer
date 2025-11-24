package com.example.features.di

import com.example.core.domain.usecase.GetStationsUseCase
import com.example.features.stations.StationsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featuresModule = module {
    factory { GetStationsUseCase(get()) }
    viewModel { StationsViewModel(get()) }
}