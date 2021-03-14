package com.example.mysuperlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ItemInnLayoutBinding
import com.example.mysuperlist.databinding.ItemLayoutBinding

class secandRecycleAdapter ( var todos:List<inn_card>):RecyclerView.Adapter<secandRecycleAdapter.ViewHolder>()
{
    class ViewHolder (val binding: ItemInnLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(todo:inn_card) {
            binding.checkBox.isChecked = todo.check
            binding.cardInnTitle.text = todo.inn_title
        }
        init {
            binding.cardInnRemove.setOnClickListener {
                val position: Int = adapterPosition
                inn_cardList.removeAt(position)
                update_secand_screen(SecandActivity())
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemInnLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val todo = todos[position]
        holder.bind(todo)
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}