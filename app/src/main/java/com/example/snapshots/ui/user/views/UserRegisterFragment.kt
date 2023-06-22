package com.example.snapshots.ui.user.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.snapshots.R
import com.example.snapshots.databinding.FragmentUserRegisterBinding

class UserRegisterFragment : Fragment() {

    var binding: FragmentUserRegisterBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRegisterBinding.inflate(
            LayoutInflater.from(context),null, false)
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UserRegisterFragment().apply {
            }
    }
}