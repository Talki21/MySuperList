package com.example.mysuperlist

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.data.card
import com.example.mysuperlist.databinding.ItemLayoutBinding


class FrontRecycleAdapter(
    private var cards: MutableList<card>,
    private val oncardclicked: (Int) -> Unit,
    private val oncardRemoved: (Int) -> Unit,
    private val oncardEdit: (Int) -> Unit
) : RecyclerView.Adapter<FrontRecycleAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            card: card, position: Int,
            oncardclicked: (Int) -> Unit,
            oncardRemoved: (Int) -> Unit,
            oncardEdit: (Int) -> Unit
        ) {
            binding.cardTitle.text = card.Title
            binding.progressBar.progress = card.Progress
            binding.prosent.text = "${card.Progress}%"
            binding.cardCount.text = "(${card.list.count()})"
            binding.cardItem.setOnClickListener {
                oncardclicked(position)
            }
            binding.cardRemove.setOnClickListener {
                oncardRemoved(position)
            }
            binding.cardEdit.setOnClickListener {
                oncardEdit(position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card, position, oncardclicked, oncardRemoved, oncardEdit)

    }

    override fun getItemCount(): Int {
        return cards.size
    }

}