package com.e.users.ui.fragments.photos;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.users.data.pojos.PhotosPojo
import com.e.users.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso

class PhotosAdapter(private var pojoList: List<PhotosPojo>) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    //    lateinit var onItemClick: ((PhotosPojo) -> Unit)

    fun setData(pojos: List<PhotosPojo>) {
        pojos.also { this.pojoList = it }
    }

    inner class PhotosViewHolder(var binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pojo: PhotosPojo) {
            binding.tvTitle.text = pojo.title
            Picasso.get()
                .load(pojo.thumbnailUrl)
                .into(binding.imgPhoto)
        }

//        init {
//            itemView.setOnClickListener {
//                onItemClick.invoke(pojoList[adapterPosition])
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(pojoList[position])
    }

    override fun getItemCount(): Int = pojoList.size
}