package com.e.users.ui.fragments.postDetails;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.users.data.pojos.PostCommentsPojo
import com.e.users.databinding.ItemCommentBinding

class PostCommentsAdapter : RecyclerView.Adapter<PostCommentsAdapter.PostCommentsViewHolder>() {

    private lateinit var pojoList: List<PostCommentsPojo>
    //    lateinit var onItemClick: ((PostCommentsPojo) -> Unit)

    fun setData(pojos: List<PostCommentsPojo>) {
        pojos.also { this.pojoList = it }
    }

    inner class PostCommentsViewHolder(var binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pojo: PostCommentsPojo) {

            binding.tvComUsername.text = pojo.name
            binding.tvComEmail.text = pojo.email
            binding.tvComBody.text = pojo.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostCommentsViewHolder {
        return PostCommentsViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostCommentsViewHolder, position: Int) {
        holder.bind(pojoList[position])
    }

    override fun getItemCount(): Int = pojoList.size
}