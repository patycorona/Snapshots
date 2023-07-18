package com.example.snapshots.ui.home.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.snapshots.databinding.FragmentItemSnapshotBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemSnapshotFragment : Fragment() {

    lateinit var binding : FragmentItemSnapshotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentItemSnapshotBinding.inflate(LayoutInflater.from(context),null, false)
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ItemSnapshotFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}