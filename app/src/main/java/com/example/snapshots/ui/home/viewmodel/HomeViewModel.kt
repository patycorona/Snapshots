package com.example.snapshots.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.ConstantGeneral.Companion.CODE
import com.example.snapshots.domain.model.ConstantGeneral.Companion.ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_LOGIN_SUCCESS
import com.example.snapshots.domain.model.ResultSnapshotModel
import com.example.snapshots.domain.usecase.FirebaseDatabaseCustomUseCase
import com.example.snapshots.domain.usecase.SnapshotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var snapshotUseCase: SnapshotUseCase,
    var firebaseDatabaseCustomUseCase: FirebaseDatabaseCustomUseCase
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
                        code = CODE,
                        message = MSG_LOGIN_SUCCESS,
                        isSuccess = true,
                        listSnapshotModel = getSsdb
                    )
                )
            }, {
                list_allSnapshots.postValue(
                    ResultSnapshotModel(
                        code = ERROR,
                        message = MSG_ERROR,
                        isSuccess = false
                    )
                )
            })
    }

    fun deleteSnapshot(id:String){
        compositeDisposable += snapshotUseCase.deleteSnapshot(id)
            .subscribeOn(Schedulers.io())
            .subscribe({ getSsdb ->
                list_allSnapshots.postValue(
                    ResultSnapshotModel(
                        code = CODE,
                        message = MSG_LOGIN_SUCCESS,
                        isSuccess = true,
                        listSnapshotModel = getSsdb
                    )
                )
            }, {
                list_allSnapshots.postValue(
                    ResultSnapshotModel(
                        code = ERROR,
                        message = MSG_ERROR,
                        isSuccess = false
                    )
                )
            })
    }

    fun setLike(snapshot: SnapshotResponse, checked: Boolean){
        compositeDisposable += firebaseDatabaseCustomUseCase.setLike(snapshot,checked)
            .subscribeOn(Schedulers.io())
            .subscribe({ getSsdb ->
                list_allSnapshots.postValue(
                    ResultSnapshotModel(
                        code = CODE,
                        message = MSG_LOGIN_SUCCESS,
                        isSuccess = true,
                        listSnapshotModel = getSsdb
                    )
                )
            }, {
                list_allSnapshots.postValue(
                    ResultSnapshotModel(
                        code = ERROR,
                        message = MSG_ERROR,
                        isSuccess = false
                    )
                )
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}