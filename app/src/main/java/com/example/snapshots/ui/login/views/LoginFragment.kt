package com.example.snapshots.ui.login.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.snapshots.databinding.FragmentLoginBinding
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.UserModel
import com.example.snapshots.ui.MainActivity
import com.example.snapshots.ui.component.Screen
import com.example.snapshots.ui.login.viewmodel.FirebaseAuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null
    private val firebaseAuthViewModel: FirebaseAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(LayoutInflater.from(context),null,false)

        initObserver()
        initListener()
        return binding?.root
    }

    private fun initListener() {

        binding?.apply {

            btnRegistro.setOnClickListener {
                (activity as MainActivity)
                    .changeScreen(Screen.UserRegisterFragment)
            }

            btnEntrar.setOnClickListener {
                validaCampos(
                    edUserName.text.toString(),
                    edPwd.text.toString()
                )
            }
        }
    }

    private var ResultObserver  = Observer<ResultModel> { resultModel ->
        if (resultModel.code == ConstantGeneral.CODE) {
            Toast.makeText(
                requireContext(), ConstantGeneral.MSG_LOGIN_SUCCESS,
                Toast.LENGTH_SHORT
            ).show()
            (activity as MainActivity)
                .changeScreen(Screen.HomeFragment)
        } else {
            Toast.makeText(
                requireContext(), ConstantGeneral.MSG_ERROR,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initObserver(){
        firebaseAuthViewModel.userResultModel.observe(viewLifecycleOwner, ResultObserver)
    }

    fun validaCampos(user:String, pwd:String){
         if (user.isNullOrEmpty().not() && pwd.isNullOrEmpty().not()){
             var userModel = UserModel(email = user, pwd = pwd )
             loginFireBase(userModel)

         }
     }

    fun loginFireBase(userModel: UserModel) {
        firebaseAuthViewModel.loginFireBase(userModel)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
            }
    }
}