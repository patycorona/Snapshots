package com.example.snapshots.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_ERROR
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.ResultSnapshotModel
import com.example.snapshots.domain.usecase.SnapshotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var snapshotUseCase: SnapshotUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()


    val list_allSnapshots: MutableLiveData<ResultSnapshotModel> by lazy {
        MutableLiveData<ResultSnapshotModel>()
    }

    fun getSnapshotsDb() {
        compositeDisposable += snapshotUseCase.getSnapshotsDb()
            .subscribeOn(Schedulers.io())
            .subscribe({ getSsdb ->
                list_allSnapshots.postValue(
                    ResultSnapshotModel(
                        code = "0",
                        message = MSG_ERROR,
                        isSuccess = true,
                        listSnapshotModel = getSsdb
                    )
                )
            }, {
                list_allSnapshots.postValue(
                    ResultSnapshotModel(
                        code = "-1",
                        message = MSG_ERROR,
                        isSuccess = false
                    )
                )
            })
    }


    // falta agregar el metodo para liberar de memoria el compositeDisposable


}