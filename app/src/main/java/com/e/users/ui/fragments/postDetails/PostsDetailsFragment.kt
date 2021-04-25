package com.e.users.ui.fragments.postDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.users.R
import com.e.users.databinding.FragmentPostsDetailsBinding
import com.e.users.ui.main.MainActivity
import com.e.users.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class PostsDetailsFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentPostsDetailsBinding
    private var viewModel: PostsDetailsViewModel = PostsDetailsViewModel()

    private lateinit var sharedPref: SharedPref
    private var postCommentsAdapter: PostCommentsAdapter = PostCommentsAdapter()
    private var getPostID: Int = 0
    private var getPostTitle: String? = null
    private var getPostBody: String? = null

    companion object {
        fun newInstance() = PostsDetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun init() {
        binding.rvComments.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        sharedPref = SharedPref(this.requireContext())
        getPostID = sharedPref.getInt("postId")
        Timber.d("postID::%s", getPostID)
        getPostTitle = sharedPref.getString("postTitle")
        Timber.d("postTitle::%s", getPostTitle)
        getPostBody = sharedPref.getString("postBody")
        Timber.d("postBody::%s", getPostBody)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
    }

    private fun methods() {
        setupToolbar()
        init()
        getPostComments()
        setupView()
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.appbar.toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.appbar.tvTitleApp.text = "Post Details"
        binding.appbar.toolbar.setNavigationIcon(R.drawable.btn_back_toolbar)
        binding.appbar.toolbar.setNavigationOnClickListener { (activity as MainActivity).supportFragmentManager.popBackStackImmediate() }
    }

    private fun getPostComments() {
        viewModel.getPostsComments(getPostID).observe(this.requireActivity(), {
            postCommentsAdapter.setData(it)
            binding.rvComments.adapter = postCommentsAdapter
            postCommentsAdapter.notifyDataSetChanged()
        })
    }

    private fun setupView() {
        binding.tvTitle.text = getPostTitle
        binding.tvBody.text = getPostBody
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostsDetailsViewModel::class.java)
    }
}
