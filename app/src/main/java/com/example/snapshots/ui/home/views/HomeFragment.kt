package com.example.snapshots.ui.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.databinding.FragmentHomeBinding
import com.example.snapshots.domain.model.ConstantGeneral.Companion.DELETE
import com.example.snapshots.domain.model.ResultSnapshotModel
import com.example.snapshots.domain.model.SnapshotModel
import com.example.snapshots.ui.MainActivity
import com.example.snapshots.ui.component.Screen
import com.example.snapshots.ui.home.viewmodel.HomeViewModel
import com.example.snapshots.ui.home.views.adapter.SnapshotAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeAux {

    var binding: FragmentHomeBinding? = null

    private val homeViewModel: HomeViewModel by viewModels()

    private val listAllSnapshotsObserver = Observer<ResultSnapshotModel> { allSnapshotsResult ->
        if (allSnapshotsResult.isSuccess) {
            allSnapshotsResult.listSnapshotModel.let {
                val adapter = SnapshotAdapter(
                    it,
                    requireContext(),
                    onItemClickListener
                )
                binding?.recyclerView?.adapter = adapter
                adapter.notifyDataSetChanged()
                binding?.progressBar?.visibility = View.INVISIBLE
            }
        }
    }

    private var onItemClickListener: ((snapshotModel: SnapshotModel, type: String) -> Unit) =
        { snapshot, type ->
            val idSnapshot = snapshot.id

            Toast.makeText(
                requireContext(), snapshot.title.uppercase(),
                Toast.LENGTH_SHORT
            ).show()
            if (type == DELETE) {
                Toast.makeText(requireContext(), idSnapshot, Toast.LENGTH_SHORT).show()
                deleteSnapshot(idSnapshot)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            (activity as MainActivity)
                .changeScreen(Screen.LoginFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context), null, false)

        initRecycler()
        initObserver()
        homeViewModel.getSnapshotsDb()
        return binding?.root
    }

    override fun goToTop() {
        binding?.recyclerView?.smoothScrollToPosition(0)
    }

    private fun initObserver() {
        homeViewModel.list_allSnapshots.observe(viewLifecycleOwner, listAllSnapshotsObserver)
    }

    private fun deleteSnapshot(idSnapshot: String) {
        homeViewModel.deleteSnapshot(idSnapshot)
    }

    private fun setList(snapshot: SnapshotResponse, checked: Boolean) {
        homeViewModel.setLike(snapshot, checked)
    }

    private fun initRecycler() {
        val linearLayoutManager = GridLayoutManager(requireContext(), 2)
        binding?.recyclerView?.apply {
            layoutManager = linearLayoutManager
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
            }
    }
}