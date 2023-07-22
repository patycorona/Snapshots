package com.example.snapshots.ui.add.views

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.snapshots.R
import com.example.snapshots.data.model.request.SnapshotRequest
import com.example.snapshots.databinding.FragmentAddBinding
import com.example.snapshots.domain.model.ConstantGeneral.Companion.CODE
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_PHOTO_ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_PHOTO_SUCCESS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.RC_GALLERY
import com.example.snapshots.domain.model.ResultModel
import com.example.snapshots.ui.add.viewmodel.AddSnapshotViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {

    var binding: FragmentAddBinding? = null
    private var photoSelectedUri: Uri? = null
    private val addSnapshotViewModel: AddSnapshotViewModel by viewModels()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        initObserver()
    }

    private fun initListener() {
        binding?.apply {

            btnPost.setOnClickListener {
                if (edTitleInstant.text.isNullOrEmpty())
                    tvMsg.text = getString(R.string.post_message_valid_title)
                else postSnapshot() }

            btnSelect.setOnClickListener { openGallery() }
        }
    }

    private fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, RC_GALLERY)
    }

    private fun postSnapshot(){
        if(photoSelectedUri != null){
            saveSnapshot("", binding?.edTitleInstant?.text.toString().trim(),photoSelectedUri.toString())
        }
    }

    private fun saveSnapshot(key :String,title:String,  photoUrl:String){
        var snapshotR = SnapshotRequest(key,title,photoUrl)
        addSnapshotViewModel.addSnapshot(snapshotR)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == RC_GALLERY){
                photoSelectedUri = data?.data
                binding?.apply {
                    imgPhoto.setImageURI(photoSelectedUri)
                    tiTitleInstant.visibility = View.VISIBLE
                    tvMsg.text = getString(R.string.post_message_valid_title)
                    btnSelect.visibility = View.INVISIBLE
                }
            }
        }
    }

    private var addSnapshotResultObserver = Observer<ResultModel> { addSResObserve ->
        if(addSResObserve.code == CODE){
            binding?.apply {
                btnSelect?.visibility = View.INVISIBLE
                tiTitleInstant?.visibility = View.GONE
                tvMsg?.text = getString(R.string.post_message_valid_title)
                btnSelect.visibility = View.INVISIBLE
                Toast.makeText(requireContext(),MSG_PHOTO_SUCCESS,Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(),MSG_PHOTO_ERROR,Toast.LENGTH_SHORT).show()
            binding?.btnSelect?.visibility = View.VISIBLE
        }
    }

    private fun initObserver() {
        addSnapshotViewModel.snapshotResultModel.observe(viewLifecycleOwner, addSnapshotResultObserver)
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