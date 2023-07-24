package com.example.snapshots.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.snapshots.R
import com.example.snapshots.data.model.request.UserRequest
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.UserModel
import com.example.snapshots.domain.usecase.FbAuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FirebaseAuthViewModel @Inject constructor(
    var fbAuthUseCase: FbAuthUseCase
):ViewModel(){
    private val compositeDisposable = CompositeDisposable()

    val userResultModel: MutableLiveData<ResultModel> by lazy {
        MutableLiveData<ResultModel>()
    }
    fun loginFireBase(userModel: UserModel){
        val userModel = UserRequest(email = userModel.email, pwd = userModel.pwd)

        compositeDisposable += fbAuthUseCase.loginFireBase(userModel)
            .subscribeOn(Schedulers.io())
            .subscribe({ loginResponse ->
                userResultModel.postValue(loginResponse)
            }, {
                userResultModel.postValue(
                    ResultModel(
                        code = ConstantGeneral.ERROR,
                        message = R.string.msg_error.toString() + " "+ ConstantGeneral.MSG_ERROR
                    )
                )
            })
    }
}