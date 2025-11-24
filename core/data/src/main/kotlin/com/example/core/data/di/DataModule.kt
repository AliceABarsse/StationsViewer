package com.example.core.data.di

import com.example.core.data.repository.StationRepositoryImpl
import com.example.core.domain.repository.StationRepository
import org.koin.dsl.module

val dataModule = module {
    single<StationRepository> { StationRepositoryImpl(networkDataSource = get ()) }
}