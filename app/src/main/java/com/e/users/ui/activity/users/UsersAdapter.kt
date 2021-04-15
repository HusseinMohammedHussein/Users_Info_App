package com.e.users.ui.activity.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.users.data.pojos.UsersPojo
import com.e.users.databinding.ItemUserBinding

/**
 * Created by Hussein on 4/15/2021
 */

class UsersAdapter() :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private lateinit var usersList: List<UsersPojo>

    fun setData(setUsersData: List<UsersPojo>) {
        this.usersList = setUsersData
        notifyDataSetChanged()
    }

    inner class UsersViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(usersPojo: UsersPojo) {
            binding.tvName.text = usersPojo.name
            binding.tvUsername.text = usersPojo.username
            binding.tvEmail.text = usersPojo.email

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) =
        holder.bind(usersList[position])

    override fun getItemCount(): Int {
        return usersList.size
    }
}