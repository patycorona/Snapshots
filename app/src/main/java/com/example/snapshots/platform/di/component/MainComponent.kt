package com.example.snapshots.platform.di.component

import com.example.snapshots.platform.di.module.RepositoryModule
import com.example.snapshots.platform.di.module.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface MainComponent
