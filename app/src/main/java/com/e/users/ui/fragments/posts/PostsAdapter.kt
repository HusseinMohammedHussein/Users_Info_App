package com.e.users.ui.fragments.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.users.data.pojos.PostsPojo
import com.e.users.databinding.ItemPostBinding

/**
 * Created by Hussein on 4/10/2021
 */

class PostsAdapter() : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    private lateinit var pojos: List<PostsPojo>
    lateinit var onItemClick: ((PostsPojo) -> Unit)

    fun setData(pojos: List<PostsPojo>) {
        pojos.also { this.pojos = it }
    }

    inner class PostViewHolder(var binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pojo: PostsPojo) {
            binding.tvTitle.text = pojo.title
            binding.tvBody.text = pojo.body
        }

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(pojos[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) =
        holder.bind(pojos[position])

    override fun getItemCount(): Int = pojos.size
}