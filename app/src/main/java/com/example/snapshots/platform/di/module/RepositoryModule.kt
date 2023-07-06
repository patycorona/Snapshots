package com.example.snapshots.platform.di.module

import com.example.snapshots.data.database.FirebaseActions
import com.example.snapshots.data.database.FirebaseDatabaseCustom
import com.example.snapshots.data.repository.FbDbcustomeRepositoryImpl
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
    fun userRepositoryProvider(fbActions: FirebaseActions): UserRepositoyImpl = UserRepositoyImpl(fbActions)

   /* @Provides
    fun fbAuthRepositoryProvider( coreAuth: CoreAuth):
            FbAuthRepositoryImpl = FbAuthRepositoryImpl(coreAuth)*/
}