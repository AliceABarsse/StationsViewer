package com.example.core.domain.di

import com.example.core.domain.usecase.GetStationsUseCase
import org.koin.dsl.module

val domainModule = module {
    // Define the UseCase here.
    // 'factory' is usually best for UseCases as they are stateless.
    factory { GetStationsUseCase(dataSource = get()) }
}
