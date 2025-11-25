package com.example.core.domain.di

import com.example.core.domain.usecase.GetProgramsUseCase
import com.example.core.domain.usecase.GetStationsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetStationsUseCase(dataSource = get()) }
    factory { GetProgramsUseCase(dataSource = get()) }
}
