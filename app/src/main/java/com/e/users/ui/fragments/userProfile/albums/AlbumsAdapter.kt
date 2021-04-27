package com.e.users.ui.fragments.userProfile.albums;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.users.data.pojos.AlbumsPojo
import com.e.users.databinding.ItemAlbumBinding

class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    private lateinit var pojoList: List<AlbumsPojo>
    lateinit var onItemClick: ((AlbumsPojo) -> Unit)

    fun setData(pojos: List<AlbumsPojo>) {
        pojos.also { this.pojoList = it }
    }

    inner class AlbumsViewHolder(var binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pojo: AlbumsPojo) {
            binding.tvTitle.text = pojo.title
        }

        init {
            binding.root.setOnClickListener {
                onItemClick.invoke(pojoList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return AlbumsViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(pojoList[position])
    }

    override fun getItemCount(): Int = pojoList.size
}