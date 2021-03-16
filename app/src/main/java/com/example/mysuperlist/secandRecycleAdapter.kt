package com.example.mysuperl


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.*
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ItemInnLayoutBinding
import com.example.mysuperlist.databinding.ItemLayoutBinding


class secandRecycleAdapter ( var todos:MutableList<inn_card>):RecyclerView.Adapter<secandRecycleAdapter.ViewHolder>()
{

    class ViewHolder (val binding: ItemInnLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            cardlist.forEach {
                if (it.id == card_id)
                {
                    binding.checkBox.isChecked = it.list[position].check
                    binding.cardInnTitle.text =  it.list[position].inn_title
                }
            }
        }
        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                println("11111111*******${position}")
            }
        }
        init {
            binding.cardInnRemove.setOnClickListener {
                val position: Int = adapterPosition
               cardlist.forEach {
                   if (it.id == card_id)
                   {
                       it.list.removeAt(position)
                       put_progress(card_id)
                   }
               }
            }
        }
        init {
            binding.checkBox.setOnClickListener {
                val position: Int = adapterPosition
                cardlist.forEach {
                    if (it.id == card_id)
                    {
                        if (it.list[position].check)
                        {
                            binding.checkBox.isChecked = false
                            it.list[position].check = false
                        }
                        else
                        {
                            binding.checkBox.isChecked = true
                            it.list[position].check = true
                        }
                        put_progress(card_id)
                    }
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemInnLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }
    override fun getItemCount(): Int {
        return todos.size
    }
}