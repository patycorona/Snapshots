package com.example.snapshots.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.snapshots.domain.model.AllSnapshotsResult
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.domain.model.ConstantGeneral.Companion.ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_ERROR
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.usecase.SnapshotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SnapshotViewModel @Inject constructor(
    var snapshotUseCase : SnapshotUseCase
):ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val list_allSnapshots: MutableLiveData<AllSnapshotsResult> by lazy {
        MutableLiveData<AllSnapshotsResult>()
    }

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