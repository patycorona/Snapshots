package com.example.snapshots.platform.di.module

import com.example.snapshots.data.repository.FbDbcustomeRepositoryImpl
import com.example.snapshots.domain.usecase.FirebaseDatabaseCustomUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun FirebaseDatabaseCustomUseCaseProvider(fbDbcustomeRepositoryImpl: FbDbcustomeRepositoryImpl) =
        FirebaseDatabaseCustomUseCase(fbDbcustomeRepositoryImpl)
}