package com.example.snapshots.platform.di.module

import com.example.snapshots.data.repository.FbAuthRepositoryImpl
import com.example.snapshots.data.repository.FbDbcustomeRepositoryImpl
import com.example.snapshots.data.repository.UserRepositoyImpl
import com.example.snapshots.domain.usecase.FbAuthUseCase
import com.example.snapshots.domain.usecase.FirebaseDatabaseCustomUseCase
import com.example.snapshots.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun firebaseDatabaseCustomUseCaseProvider(fbDbcustomeRepositoryImpl: FbDbcustomeRepositoryImpl) =
        FirebaseDatabaseCustomUseCase(fbDbcustomeRepositoryImpl)

    @Provides
    fun userUseCaseProvider(userRepositoryImpl : UserRepositoyImpl) =
        UserUseCase(userRepositoryImpl)

    @Provides
    fun FbAuthUseCaseProvider(fbAuthRepositoryImpl: FbAuthRepositoryImpl) =
        FbAuthUseCase(fbAuthRepositoryImpl)
}