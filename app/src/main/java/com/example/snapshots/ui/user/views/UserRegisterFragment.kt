package com.example.snapshots.ui.user.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.snapshots.R
import com.example.snapshots.databinding.FragmentUserRegisterBinding
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_COMPLETE_INFO
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_NOT_MATCH_PWD
import com.example.snapshots.domain.model.UserModel
import com.example.snapshots.ui.MainActivity
import com.example.snapshots.ui.component.Screen
import com.example.snapshots.ui.login.viewmodel.FirebaseAuthViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserRegisterFragment : Fragment() {

    var binding: FragmentUserRegisterBinding? = null
//    var userModel: UserModel? = UserModel()

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
        initListener()
        return binding?.root
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
            if (pwd == confirmPwd) {

//                userModel?.email = user
//                userModel?.pwd = pwd
//                (activity as MainActivity)
//                    .changeScreen(Screen.MainActivity,UserModel())
                registerUser(user,pwd)
            }else{
                Toast.makeText(requireContext(), MSG_NOT_MATCH_PWD,
                    Toast.LENGTH_SHORT).show()
            }

        }else{
            Toast.makeText(requireContext(), MSG_COMPLETE_INFO,
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser(email : String, password: String) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)

        FirebaseAuthViewModel().firebaseAuth(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(requireContext(), "createUserWithEmail:success",
                        Toast.LENGTH_SHORT).show()
                    val user = task.result.user
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }
    fun updateUI(user: FirebaseUser?) {
        user?.let {
            // Name, email address, and profile photo Url
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = it.uid
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            UserRegisterFragment().apply {
            }
    }
}