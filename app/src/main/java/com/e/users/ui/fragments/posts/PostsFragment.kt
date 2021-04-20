package com.e.users.ui.fragments.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.users.databinding.FragmentPostsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostsFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentPostsBinding
    private var postsViewModel: PostsViewModel = PostsViewModel()
    private var postsAdapter: PostsAdapter = PostsAdapter()

    companion object {
        fun newInstance() = PostsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        postsViewModel = ViewModelProvider(this.requireActivity()).get(PostsViewModel::class.java)
        binding.rv.run {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
        getPostsData()
    }

    private fun getPostsData() {
        postsViewModel.getPosts().observe(this.requireActivity(), {
            postsAdapter.setData(it)
            binding.rv.adapter = postsAdapter
            postsAdapter.notifyDataSetChanged()
        })
    }
}