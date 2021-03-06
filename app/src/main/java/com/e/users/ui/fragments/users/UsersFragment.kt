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
import com.e.users.utils.SharedKeys.Companion.EMAIL
import com.e.users.utils.SharedKeys.Companion.USER_ID
import com.e.users.utils.SharedKeys.Companion.USER_NAME
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
        return mBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersViewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
    }

    private fun init() {
        sharedPref = SharedPref(this.requireContext())
        mBinding.rvUsers.run {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(this.context, 2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        methods()
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

    private fun getUsersData() {
        usersViewModel.getUsers().observe(this.requireActivity(), {
            usersAdapter.setData(it)
            mBinding.rvUsers.adapter = usersAdapter
            usersAdapter.notifyDataSetChanged()
        })
    }

    private fun setupAdapter() {
        usersAdapter.onItemClick = {
            (activity as MainActivity).startFragment(UserProfileFragment())
            sharedPref.setString(USER_NAME, it.username)
            sharedPref.setString(EMAIL, it.email)
            sharedPref.setInt(USER_ID, it.id)
        }
    }
}