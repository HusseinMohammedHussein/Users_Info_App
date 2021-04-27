package com.e.users.ui.fragments.photos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.users.R
import com.e.users.databinding.FragmentPhotosBinding
import com.e.users.ui.main.MainActivity
import com.e.users.utils.SharedKeys
import com.e.users.utils.SharedKeys.Companion.ALBUM_ID
import com.e.users.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PhotosFragment @Inject constructor() : Fragment() {

    private lateinit var binding: FragmentPhotosBinding
    private lateinit var viewModel: PhotosViewModel
    private lateinit var sharedPref: SharedPref
    private var photosAdapter: PhotosAdapter = PhotosAdapter()
    private var getAlbumId: Int = 0

    companion object {
        fun newInstance() = PhotosFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhotosViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun methods() {
        setupToolbar()
        init()
        getAlbumPhotos()
    }

    private fun getAlbumPhotos() {
        viewModel.getAlbumPhotos(getAlbumId).observe(this.requireActivity(), {
            photosAdapter.setData(it)
            binding.rvPhotos.adapter = photosAdapter
            photosAdapter.notifyDataSetChanged()
        })
    }

    private fun init() {
        sharedPref = SharedPref(this.requireContext())
        getAlbumId = sharedPref.getInt(ALBUM_ID)
        binding.rvPhotos.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.appbar.toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.appbar.tvTitleApp.text = "Photos"
        binding.appbar.toolbar.setNavigationIcon(R.drawable.btn_back_toolbar)
        binding.appbar.toolbar.setNavigationOnClickListener { (activity as MainActivity).supportFragmentManager.popBackStack() }
    }
}