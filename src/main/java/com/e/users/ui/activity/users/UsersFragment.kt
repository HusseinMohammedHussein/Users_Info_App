package com.e.users.ui.activity.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.users.databinding.UsersFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UsersFragment @Inject constructor() : Fragment() {
    private lateinit var binding: UsersFragmentBinding
    private lateinit var viewModel: UsersViewModel
    private var usersAdapter: UsersAdapter =  UsersAdapter()

    companion object {
        fun newInstance() = UsersFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UsersFragmentBinding.inflate(inflater, container, false)
        getUsersData()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
    }

    private fun getUsersData() {
        viewModel.getUsers().observe(this.requireActivity(), {
            usersAdapter.setData(it)
            binding.rvUsers.adapter = usersAdapter

            binding.rvUsers.run {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
                adapter = usersAdapter
            }
        })
    }

}