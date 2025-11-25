package com.example.features.di

import com.example.core.domain.usecase.GetProgramsUseCase
import com.example.core.domain.usecase.GetStationsUseCase
import com.example.features.MainPaneViewModel
import com.example.features.programs.ProgramsViewModel
import com.example.features.stations.StationsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val featuresModule = module {

    viewModel { MainPaneViewModel() }

    factory { GetStationsUseCase(get()) }
    viewModel { StationsViewModel(get()) }

    factory { GetProgramsUseCase(get()) }
    viewModel { ProgramsViewModel(get()) }
}