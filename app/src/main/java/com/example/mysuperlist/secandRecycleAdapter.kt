package com.example.mysuperlist


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ItemInnLayoutBinding


class SecandRecycleAdapter(
    var InnCards: MutableList<inn_card>,
    private val onInnCardChecked: (Int) -> Unit,
    private val onInnCardRemoved: (Int) -> Unit,
    private val onInnCardEdit: (Int) -> Unit
) :
    RecyclerView.Adapter<SecandRecycleAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemInnLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            position: Int,
            onInnCardChecked: (Int) -> Unit,
            onInnCardRemoved: (Int) -> Unit,
            onInnCardEdit: (Int) -> Unit
        ) {
            cardlist.forEach {
                if (it.id == cardIdHolder.Card_id) {
                    binding.checkBox.isChecked = it.list[position].check
                    binding.cardInnTitle.text = it.list[position].inn_title
                    binding.checkBox.setOnClickListener {
                        onInnCardChecked(position)
                    }
                    binding.cardInnRemove.setOnClickListener {
                        onInnCardRemoved(position)
                    }
                    binding.cardInnEdit.setOnClickListener {
                        onInnCardEdit(position)
                    }
                }
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
        holder.bind(position, onInnCardChecked, onInnCardRemoved, onInnCardEdit)

    }

    override fun getItemCount(): Int {
        return InnCards.size
    }
}