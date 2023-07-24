package com.example.snapshots.ui.home.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.snapshots.databinding.FragmentItemSnapshotBinding
import com.example.snapshots.domain.model.ConstantGeneral.Companion.DELETE
import com.example.snapshots.domain.model.SnapshotModel

class SnapshotAdapter (
    private val dataSource: MutableList<SnapshotModel>,
    val context: Context,
    var onListHitItemClickListener: ((snapshotModel: SnapshotModel, type:String) -> Unit)
) : RecyclerView.Adapter<SnapshotAdapter.ViewHolder>() {

    inner class ViewHolder(
        private var binding: FragmentItemSnapshotBinding,
        private var ctx: Context,
        var onListHitItemClickListener: ((snapshotModel: SnapshotModel, type:String) -> Unit)
    ) : RecyclerView.ViewHolder(binding!!.root)
    {
        var root: ConstraintLayout = binding.layoutItem

        fun bind(dataSource: SnapshotModel){
            binding.apply{
                tvIdSnapshot?.text = dataSource.id.toString()
                tvtitle?.text = dataSource.title
                Glide.with(context)
                    .load(dataSource.photoUrl)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPhoto)
                cblike.text = dataSource.likeList.keys.size.toString()
                cblike.setOnCheckedChangeListener { compoundButton, isChecked ->
                    if (isChecked){
                        var cantLikes = dataSource.likeList.keys.size + 1
                        cblike.text = cantLikes.toString()
                    }else{
                        var cantLikes = dataSource.likeList.keys.size
                        cblike.text = cantLikes.toString()
                    }

                }
                layoutItem.setOnClickListener {
                    onListHitItemClickListener.invoke(dataSource, "")
                }

                btnDelete.setOnClickListener {
                    onListHitItemClickListener.invoke(dataSource, DELETE)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentItemSnapshotBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(binding, viewGroup.context,onListHitItemClickListener )
    }

    override fun onBindViewHolder(viewHolder: SnapshotAdapter.ViewHolder, position: Int) {
        val snapshotModel: SnapshotModel = dataSource[position]
        viewHolder.bind(snapshotModel)
    }

    override fun getItemCount() = dataSource.size

}