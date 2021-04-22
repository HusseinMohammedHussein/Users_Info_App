package com.e.users.ui.fragments.userProfile;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.users.data.pojos.PostsPojo
import com.e.users.databinding.ItemPostBinding

class UsersPostsAdapter : RecyclerView.Adapter<UsersPostsAdapter.UsersPostsViewHolder>() {

    private lateinit var pojoList: List<PostsPojo>
    lateinit var onItemClick: ((PostsPojo) -> Unit)

    fun setData(pojos: List<PostsPojo>) {
        pojos.also { this.pojoList = it }
    }

    inner class UsersPostsViewHolder(var binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pojo: PostsPojo) {
            binding.tvTitle.text = pojo.title
            binding.tvBody.text = pojo.body
        }

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(pojoList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersPostsViewHolder {
        return UsersPostsViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersPostsViewHolder, position: Int) {
        holder.bind(pojoList[position])
    }

    override fun getItemCount(): Int = pojoList.size
}