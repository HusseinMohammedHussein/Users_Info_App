package com.e.users.ui.fragments.todos;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.users.data.pojos.TodoPojo
import com.e.users.databinding.ItemTodoBinding

class TODOsAdapter : RecyclerView.Adapter<TODOsAdapter.TODOsViewHolder>() {

    private lateinit var pojoList: List<TodoPojo>
//    lateinit var onItemClick: ((TodoPojo) -> Unit)

    fun setData(pojos: List<TodoPojo>) {
        pojos.also { this.pojoList = it }
    }

    inner class TODOsViewHolder(var binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pojo: TodoPojo) {
            binding.checkbox.text = pojo.title
            binding.checkbox.isChecked = pojo.completed
        }

//        init {
//            itemView.setOnClickListener {
//                onItemClick.invoke(pojoList[adapterPosition])
//            }
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TODOsViewHolder {
        return TODOsViewHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TODOsViewHolder, position: Int) =
        holder.bind(pojoList[position])

    override fun getItemCount(): Int = pojoList.size
}