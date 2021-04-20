package com.e.users.ui.fragments.userProfile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.users.R
import com.e.users.databinding.FragmentUserProfileBinding
import com.e.users.ui.activity.main.MainActivity
import com.e.users.ui.fragments.postDetails.PostsDetailsFragment
import com.e.users.ui.fragments.todos.TODOsFragment
import com.e.users.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class UserProfileFragment @Inject constructor() : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    private var viewModel: UserProfileViewModel = UserProfileViewModel()
    private lateinit var sharedPref: SharedPref

    private var postsAdapter: UsersPostsAdapter = UsersPostsAdapter()

    private var getUsername: String? = null
    private var getEmail: String? = null
    private var getUserId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        setupToolbar()
        sharedPref = SharedPref(this.requireContext())
        getUsername = sharedPref.getString("username")
        Timber.d("getUsername::%s", getUsername)
        getEmail = sharedPref.getString("email")
        Timber.d("getEmail::%s", getEmail)
        getUserId = sharedPref.getInt("userId")
        Timber.d("getUserId::%s", getUserId)
        setupViews()
        getUserPosts()
        initRecyclerView()
    }

    private fun getUserPosts() {
        viewModel.getUserPosts(getUserId).observe(this.requireActivity(), { it ->
            postsAdapter.setData(it)
            binding.rvPosts.adapter = postsAdapter
            postsAdapter.notifyDataSetChanged()
            postsAdapter.onItemClick = {
                (activity as MainActivity).startFragment(PostsDetailsFragment())
                sharedPref.setInt("postId", it.id)
                sharedPref.setString("postTitle", it.title)
                sharedPref.setString("postBody", it.body)
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvPosts.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(binding.appbar.toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.appbar.tvTitleApp.text = "User Profile"
        binding.appbar.toolbar.setNavigationIcon(R.drawable.btn_back_toolbar)
        binding.appbar.toolbar.setNavigationOnClickListener { (activity as MainActivity).supportFragmentManager.popBackStack() }
    }

    private fun setupViews() {
        binding.tvUsername.text = getUsername
        binding.tvEmail.text = getEmail

        binding.btnTodos.setOnClickListener {
            (activity as MainActivity).startFragment(TODOsFragment())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
    }
}