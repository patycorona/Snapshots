package com.example.snapshots.ui.user.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.snapshots.R
import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.domain.model.ConstantGeneral.Companion.ERROR
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    var userUseCase: UserUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val userResultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }

    fun userRegisterFirebase(userRequest: UserRequest){

        compositeDisposable += userUseCase.userRegisterFirebase(userRequest)
            .subscribeOn(Schedulers.io())
            .subscribe({ userRegister ->
                userResultModel.postValue(userRegister)
            }, {
                userResultModel.postValue(
                    ResultModel(
                        code = ERROR,
                        message = R.string.msg_error.toString()
                    )
                )
            })
    }
}