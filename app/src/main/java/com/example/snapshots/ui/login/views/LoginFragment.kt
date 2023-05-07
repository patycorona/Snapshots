package com.example.snapshots.ui.login.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.snapshots.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null
    lateinit var auth: FirebaseAuth
   // private val fbAuthViewModel: FirebaseAuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance()

        binding = FragmentLoginBinding.inflate(LayoutInflater.from(context),null,false)


        initListener()

        return binding?.root
    }

    private fun initListener()
    {

        binding?.apply {


            var email = edUserName.text.toString()
            var pwd = edPwd.text.toString()

            btnEntrar.setOnClickListener { authUser(email,pwd) }
        }
    }

    private fun registerUser(email : String, password: String) {

//        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
/*
        FirebaseAuthViewModel().firebaseAuth(email, password)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Toast.makeText(baseContext, "createUserWithEmail:success",
                    Toast.LENGTH_SHORT).show()
                val user = task.result.user
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
                updateUI(null)
            }
        }*/
    }

    fun authUser(email : String, password: String) {

       // fbAuthViewModel.firebaseAuth(email,password)

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()){ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(requireContext(), "signInWithEmail:success",
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
            LoginFragment().apply {
            }
    }
}