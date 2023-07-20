package com.example.snapshots.ui.add.views

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toFile
import com.example.snapshots.R
import com.example.snapshots.databinding.FragmentAddBinding
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_PHOTO_ERROR
import com.example.snapshots.domain.model.ConstantGeneral.Companion.MSG_PHOTO_SUCCESS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.PATH_SNAPSHOTS
import com.example.snapshots.domain.model.ConstantGeneral.Companion.RC_GALLERY
import com.example.snapshots.domain.model.SnapshotModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddFragment : Fragment() {

    var binding: FragmentAddBinding? = null
    private var photoSelectedUri: Uri? = null
    private lateinit var storageReference: StorageReference
    private lateinit var  databasePreference: DatabaseReference

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
        storageReference = FirebaseStorage.getInstance().reference
        databasePreference = FirebaseDatabase.getInstance().reference.child(PATH_SNAPSHOTS)
        initListener()
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
        binding?.progressBar?.visibility = View.VISIBLE
        val storageReb = storageReference.child(PATH_SNAPSHOTS).child(R.string.nameFolder.toString())
        val key = databasePreference.push().key!!

        if(photoSelectedUri != null){
            storageReb.putFile(photoSelectedUri!!)
                .addOnProgressListener {
                    val progress = (100 * it.bytesTransferred/it.totalByteCount).toInt()
                    binding?.progressBar?.progress = progress.toInt()
                    binding?.tvMsg?.text = "$progress %"
                }
                .addOnCompleteListener {
                    binding?.progressBar?.visibility = View.INVISIBLE
                    binding?.btnSelect?.visibility = View.INVISIBLE
                }
                .addOnSuccessListener {
                    binding?.root?.let { it1 ->
                        Snackbar.make(it1, MSG_PHOTO_SUCCESS, Snackbar.LENGTH_SHORT).show()
                        it.storage.downloadUrl.addOnSuccessListener{
                            saveSnapshot(key, binding?.edTitleInstant?.text.toString().trim(),it.toString())

                            binding?.tiTitleInstant?.visibility = View.GONE
                            binding?.tvMsg?.text = getString(R.string.post_message_valid_title)
                        }
                    }
                }
                .addOnFailureListener{
                    binding?.root?.let { it1 ->
                        Snackbar.make(it1,MSG_PHOTO_ERROR,Snackbar.LENGTH_SHORT).show() }
                }
        }
    }

    private fun saveSnapshot(key :String,title:String,  url:String){
        val snapshot = SnapshotModel(key, title, url)
        databasePreference.child(key).setValue(snapshot)
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
                }
            }
        }
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