package com.e.users.ui.fragments.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.e.users.databinding.FragmentUsersBinding
import com.e.users.ui.fragments.userProfile.UserProfileFragment
import com.e.users.ui.main.MainActivity
import com.e.users.utils.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment @Inject constructor() : Fragment() {
    private lateinit var mBinding: FragmentUsersBinding
    private var usersViewModel: UsersViewModel = UsersViewModel()
    private var usersAdapter: UsersAdapter = UsersAdapter()
    private lateinit var sharedPref: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentUsersBinding.inflate(inflater, container, false)
        methods()
        return mBinding.root
    }

    private fun methods() {
        setupToolbar()
        init()
        getUsersData()
        setupAdapter()
    }

    @SuppressLint("SetTextI18n")
    private fun setupToolbar() {
        (activity as MainActivity).setSupportActionBar(mBinding.appbar.toolbar)
        (activity as MainActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mBinding.appbar.tvTitleApp.text = "Users"
    }

    private fun init() {
        sharedPref = SharedPref(this.requireContext())
        mBinding.rvUsers.run {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(this.context, 2)
        }
    }

    private fun getUsersData() {
        usersViewModel.getUsers().observe(this.requireActivity(), {
            usersAdapter.setData(it)
            mBinding.rvUsers.adapter = usersAdapter
            mBinding.rvUsers.adapter = usersAdapter
            usersAdapter.notifyDataSetChanged()
        })
    }

    private fun setupAdapter() {
        usersAdapter.onItemClick = {
            (activity as MainActivity).startFragment(UserProfileFragment())
            sharedPref.setString("username", it.username)
            sharedPref.setString("email", it.email)
            sharedPref.setInt("userId", it.id)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
    }
}