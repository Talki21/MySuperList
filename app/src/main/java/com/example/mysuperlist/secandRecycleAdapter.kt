package com.example.mysuperlist


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ItemInnLayoutBinding


class SecandRecycleAdapter(var InnCards: MutableList<inn_card>) :
    RecyclerView.Adapter<SecandRecycleAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemInnLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            cardlist.forEach {
                if (it.id == cardIdHolder.Card_id) {
                    binding.checkBox.isChecked = it.list[position].check
                    binding.cardInnTitle.text = it.list[position].inn_title
                }
            }
        }

        init {
            binding.cardInnRemove.setOnClickListener {
                val position: Int = adapterPosition
                cardlist.forEach {
                    if (it.id == cardIdHolder.Card_id) {
                        it.list.removeAt(position)
                        put_progress(cardIdHolder.Card_id)
                    }
                }
                ref.child(auth.uid.toString()).setValue(cardlist)
                update_secand_screen()
            }
        }

        init {
            binding.checkBox.setOnClickListener {
                val position: Int = adapterPosition
                cardlist.forEach {
                    if (it.id == cardIdHolder.Card_id) {
                        if (it.list[position].check) {
                            binding.checkBox.isChecked = false
                            it.list[position].check = false
                        } else {
                            binding.checkBox.isChecked = true
                            it.list[position].check = true
                        }
                        put_progress(cardIdHolder.Card_id)
                    }
                }
                ref.child(auth.uid.toString()).setValue(cardlist)
                update_secand_screen()
            }
        }

        init {
            binding.cardInnEdit.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(it.context, AddInnCardActivity::class.java)
                intent.putExtra("position2", position)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemInnLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)

    }

    override fun getItemCount(): Int {
        return InnCards.size
    }
}