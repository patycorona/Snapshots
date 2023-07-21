package com.example.snapshots.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.snapshots.domain.usecase.SnapshotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    var snapshotUseCase : SnapshotUseCase
):ViewModel() {
//    private val compositeDisposable = CompositeDisposable()
//
//    val list_allSnapshots: MutableLiveData<AllSnapshotsResult> by lazy {
//        MutableLiveData<AllSnapshotsResult>()
//    }

    fun getSnapshotsDb()
    {
//        compositeDisposable += snapshotUseCase.getSnapshotsDb()
//            .subscribeOn(Schedulers.io())
//            .subscribe({ getSsdb ->
//                list_allSnapshots.postValue(getSsdb)
//            }, {
//                list_allSnapshots.postValue(
//                    ResultModel(
//                        code = ERROR,
//                        message = MSG_ERROR,
//                        isSuccess = false
//                    )
//                )
//            })
    }

}