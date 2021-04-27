package com.e.users.ui.fragments.userProfile.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.users.R
import com.e.users.databinding.FragmentAlbumsBinding
import com.e.users.ui.fragments.photos.PhotosFragment
import com.e.users.ui.main.MainActivity
import com.e.users.utils.SharedKeys.Companion.ALBUM_ID
import com.e.users.utils.SharedKeys.Companion.USER_ID
import com.e.users.utils.SharedPref
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumsBottomSheet @Inject constructor() : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAlbumsBinding
    private var viewModel: AlbumsViewModel = AlbumsViewModel()
    private val adapter: AlbumsAdapter = AlbumsAdapter()
    private lateinit var sharedPref: SharedPref
    private var getUserId: Int = 0

    companion object {
        fun newInstance() = AlbumsBottomSheet()
        const val TAG: String = "AlbumsBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AlbumsViewModel::class.java)
        dialog?.window?.attributes?.windowAnimations = R.style.MaterialDialogSheet
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun methods() {
        init()
        getUserAlbums()
        onItemClick()
    }

    private fun onItemClick() {
        adapter.onItemClick = {
            (activity as MainActivity).startFragment(PhotosFragment())
            sharedPref.setInt(ALBUM_ID, it.id)
            dismiss()
        }
    }

    private fun init() {
        sharedPref = SharedPref(this.requireContext())
        getUserId = sharedPref.getInt(USER_ID)
        binding.rvAlbums.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        adapter
    }

    private fun getUserAlbums() {
        viewModel.getUserAlbums(getUserId).observe(this, {
            adapter.setData(it)
            binding.rvAlbums.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }
}