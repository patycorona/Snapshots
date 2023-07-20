package com.example.snapshots.ui.home.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.snapshots.R
import com.example.snapshots.databinding.FragmentHomeBinding
import com.example.snapshots.databinding.FragmentItemSnapshotBinding
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.ui.MainActivity
import com.example.snapshots.ui.component.Screen
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    var binding:FragmentHomeBinding? = null
    lateinit var firebaseAdapter: FirebaseRecyclerAdapter<SnapshotResponse, SnapshotHolder>
    lateinit var mlayoutManager: RecyclerView.LayoutManager

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

        binding = FragmentHomeBinding.inflate(LayoutInflater.from(context),null, false)
        return binding?.root
    }

    inner class SnapshotHolder(view:View): RecyclerView.ViewHolder(view){
       val mbinding = FragmentItemSnapshotBinding.bind(view)
        fun setListener(snapshot: SnapshotResponse){

       }
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val query = FirebaseDatabase.getInstance().reference
            .child(ConstantGeneral.PATH_SNAPSHOTS)
        val options = FirebaseRecyclerOptions.Builder<SnapshotResponse>()
            .setQuery(query, SnapshotResponse::class.java).build()


        firebaseAdapter = object : FirebaseRecyclerAdapter<SnapshotResponse, SnapshotHolder>(options) {
            private lateinit var mContext: android.content.Context
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapshotHolder {
                mContext = parent.context

                val view = LayoutInflater.from(mContext)
                    .inflate(R.layout.fragment_item_snapshot, parent, false)
                return SnapshotHolder(view)
            }

            override fun onBindViewHolder(holder: SnapshotHolder, position: Int, model: SnapshotResponse) {
                val snapshot = getItem(position)

                with(holder) {
                    setListener(snapshot)

                    mbinding.apply {
                        tvtitle?.text = snapshot.title
                        Glide.with(mContext)
                            .load(snapshot.photoUrl)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop()
                            .into(imgPhoto)
                    }
                }
            }

            override fun onDataChanged() {
                super.onDataChanged()
                binding?.progressBar?.visibility = View.GONE
                notifyDataSetChanged()
            }

             override fun onError(error: DatabaseError) {
                super.onError(error)
                Toast.makeText(mContext, error.message, Toast.LENGTH_SHORT).show()
            }
        }// end adapter

        mlayoutManager = LinearLayoutManager(context)

        binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = mlayoutManager
            adapter = firebaseAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        firebaseAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        firebaseAdapter.stopListening()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {

            }
    }
}