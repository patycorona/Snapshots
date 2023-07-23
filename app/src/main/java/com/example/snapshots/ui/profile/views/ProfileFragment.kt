package com.example.snapshots.ui.profile.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.snapshots.R
import com.example.snapshots.databinding.FragmentProfileBinding
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_SIGN_OUT
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    var binding : FragmentProfileBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(LayoutInflater.from(context),null,false)

        binding?.btnLogout?.setOnClickListener { singOut() }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            tvName.text = FirebaseAuth.getInstance().currentUser?.displayName
            tvEmail.text = FirebaseAuth.getInstance().currentUser?.email
        }
    }

    private fun singOut() {

            AuthUI.getInstance().signOut(requireContext())
                .addOnCompleteListener {
                    Toast.makeText(requireContext(), MSG_SIGN_OUT, Toast.LENGTH_SHORT).show()
                }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}