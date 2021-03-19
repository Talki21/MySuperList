package com.example.mysuperl



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.*
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ItemInnLayoutBinding



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
            binding.cardInnRemove.setOnClickListener {
                val position: Int = adapterPosition
               cardlist.forEach {
                   if (it.id == card_id)
                   {
                       it.list.removeAt(position)
                       put_progress(card_id)
                   }
               }
                ref.setValue(cardlist)
                update_secand_screen(SecandActivity(), card_id)
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
                ref.setValue(cardlist)
                update_secand_screen(SecandActivity(), card_id)
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