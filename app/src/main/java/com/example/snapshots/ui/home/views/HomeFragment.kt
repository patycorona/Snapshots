package com.example.snapshots.ui.home.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.snapshots.R
import com.example.snapshots.data.model.response.SnapshotResponse
import com.example.snapshots.databinding.FragmentHomeBinding
import com.example.snapshots.databinding.FragmentItemSnapshotBinding
import com.example.snapshots.domain.model.ConstantGeneral
import com.example.snapshots.ui.MainActivity
import com.example.snapshots.ui.component.Screen
import com.example.snapshots.ui.home.viewmodel.HomeViewModel
import com.example.snapshots.ui.login.viewmodel.FirebaseAuthViewModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import io.reactivex.Observable
import kotlinx.coroutines.flow.callbackFlow

@AndroidEntryPoint
class HomeFragment : Fragment() {

    var binding:FragmentHomeBinding? = null
    lateinit var firebaseAdapter: FirebaseRecyclerAdapter<SnapshotResponse, SnapshotHolder>
    lateinit var mlayoutManager: RecyclerView.LayoutManager
    private lateinit var auth: FirebaseAuth

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            (activity as MainActivity)
                .changeScreen(Screen.LoginFragment)
        }

        auth = Firebase.auth
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

     //   getSnapshotsDb()

         homeViewModel.getSnapshotsDb()

//        buildAdapter()


        mlayoutManager = LinearLayoutManager(context)

        binding?.recyclerView?.apply {
            setHasFixedSize(true)
            layoutManager = mlayoutManager
            adapter = firebaseAdapter
        }
    }


/*
    fun getSnapshotsDb() {
        val query = FirebaseDatabase.getInstance().reference
            .child(ConstantGeneral.PATH_SNAPSHOTS)


        val obs: Observable<MutableList<SnapshotResponse>> = Observable.create { emitter ->
            query.child(ConstantGeneral.PATH_SNAPSHOTS).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list: MutableList<SnapshotResponse> = ArrayList<SnapshotResponse>()
                    val snapshotIterable = snapshot.children
                    val iterator: Iterator<DataSnapshot> = snapshotIterable.iterator()

                    while (iterator.hasNext()) {
                        val snapshotResponse: SnapshotResponse =
                            iterator.next().getValue(SnapshotResponse::class.java)
                                ?: SnapshotResponse()
                        list.add(snapshotResponse)
                    }
                    emitter.onNext(list)
                }

                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(FirebaseException(error.message))
                }
            })
        }*/


//        return obs.flatMap { list -> Observable.just(list) }

//        return Single.just(
//            FirebaseRecyclerOptions.Builder<SnapshotResponse>()
//            .setQuery(query, SnapshotResponse::class.java).build())
    //}

    fun buildAdapter() {

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
                Toast.makeText(context, "HOME-" + error.message, Toast.LENGTH_SHORT).show()
            }
        }// end adapter

    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
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