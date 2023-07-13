package com.example.snapshots.ui.add.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.snapshots.R
import com.example.snapshots.databinding.FragmentAddBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {

    var binding: FragmentAddBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddBinding.inflate(LayoutInflater.from(context),null,false)
        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AddFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}