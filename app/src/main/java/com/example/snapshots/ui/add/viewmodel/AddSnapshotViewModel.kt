package com.example.snapshots.ui.add.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.snapshots.R
import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.domain.model.ConstantGeneral.Companion.ERROR
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.usecase.SnapshotUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AddSnapshotViewModel @Inject constructor (
    var snapshotUseCase: SnapshotUseCase
        ): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val snapshotResultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    fun addSnapshot(snapshotR : SnapshotRequest){
        compositeDisposable += snapshotUseCase.addSnapshot(snapshotR)
            .subscribeOn(Schedulers.io())
            .subscribe({ userRegister ->
                snapshotResultModel.postValue(userRegister)
            }, {
                snapshotResultModel.postValue(
                    ResultModel(
                        code = ERROR,
                        message = R.string.msg_error.toString()
                    )
                )
            })
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}