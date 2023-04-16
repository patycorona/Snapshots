package com.example.snapshots.platform.di.module

import com.example.snapshots.data.database.FirebaseDatabaseCustom
import com.example.snapshots.data.repository.FbDbcustomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun fbDbCustomRepositoryProvider( firebaseDatabaseCustom: FirebaseDatabaseCustom):
            FbDbcustomeRepositoryImpl = FbDbcustomeRepositoryImpl(firebaseDatabaseCustom)
}