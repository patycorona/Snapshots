package com.example.snapshots.ui.user.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.snapshots.databinding.FragmentUserRegisterBinding
import com.example.snapshots.domain.model.ConstantGeneral.Companion.CODE
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_COMPLETE_INFO
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_NOT_MATCH_PWD
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_REGISTER_SUCCESS
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.domain.model.UserModel
import com.example.snapshots.ui.MainActivity
import com.example.snapshots.ui.component.Screen
import com.example.snapshots.ui.user.viewmodel.UserViewModel
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserRegisterFragment : Fragment() {

    var binding: FragmentUserRegisterBinding? = null
    private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        FirebaseApp.initializeApp(requireContext())

        binding = FragmentUserRegisterBinding.inflate(
            LayoutInflater.from(context),null, false)
        initObserver()
        initListener()

        return binding?.root
    }

    private var ResultObserver  = Observer<ResultModel> { resultModel ->
        if (resultModel.code == CODE) {
            Toast.makeText(
                requireContext(), MSG_REGISTER_SUCCESS,
                Toast.LENGTH_SHORT
            ).show()
            (activity as MainActivity)
                .changeScreen(Screen.LoginFragment)
        } else {
            Toast.makeText(
                requireContext(), MSG_ERROR,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun initObserver(){
        userViewModel.userResultModel.observe(viewLifecycleOwner, ResultObserver)
    }

    private fun initListener(){
        binding?.apply {
            btnRegistar?.setOnClickListener { validaCampos(edUserName.text.toString()
                ,edPwd.text.toString(),edConfirmPwd.text.toString())}
        }
    }

    private fun validaCampos(user:String, pwd:String, confirmPwd:String){
        if (user.isNullOrEmpty().not() && pwd.isNullOrEmpty().not() &&
            confirmPwd.isNullOrEmpty().not()){

            var userModel = UserModel(email = user, pwd = pwd )

            if (pwd == confirmPwd) registerUser(userModel)
            else Toast.makeText(requireContext(), MSG_NOT_MATCH_PWD,
                Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(requireContext(), MSG_COMPLETE_INFO,
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser(userModel: UserModel) {
        userViewModel.userRegisterFirebase(userModel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UserRegisterFragment().apply {
            }
    }
}