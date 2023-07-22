package com.example.snapshots.platform.di.module

import com.example.snapshots.data.database.FirebaseActions
import com.example.snapshots.data.database.FirebaseDatabaseCustom
import com.example.snapshots.data.repository.FbAuthRepositoryImpl
import com.example.snapshots.data.repository.FbDbcustomeRepositoryImpl
import com.example.snapshots.data.repository.SnapshotRepositoryImpl
import com.example.snapshots.data.repository.UserRepositoyImpl
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
    @Provides
    fun userRepositoryProvider(fbActions: FirebaseActions): UserRepositoyImpl = UserRepositoyImpl(fbActions)

    @Provides
    fun fbAuthRepositoryProvider(fbActions: FirebaseActions):
            FbAuthRepositoryImpl = FbAuthRepositoryImpl(fbActions)

    @Provides
    fun firebaseDatabaseCustomProvider():
            FirebaseDatabaseCustom = FirebaseDatabaseCustom()

    @Provides
    fun snapshotRepositoryProvider(fbActions: FirebaseActions, firebaseDatabaseCustom: FirebaseDatabaseCustom):
            SnapshotRepositoryImpl = SnapshotRepositoryImpl(fbActions, firebaseDatabaseCustom)
}