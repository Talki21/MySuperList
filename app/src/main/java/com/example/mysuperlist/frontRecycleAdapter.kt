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
    private var cards: MutableList<card>
) : RecyclerView.Adapter<FrontRecycleAdapter.ViewHolder>() {
    class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: card, position: Int) {
            binding.cardTitle.text = card.Title
            binding.progressBar.progress = card.Progress
            binding.prosent.text = "${card.Progress}%"
            binding.cardCount.text = "(${card.list.count()})"

        }

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(it.context, SecandActivity::class.java)
                cardIdHolder.Card_id = cardlist[position].id
                intent.putExtra("id", cardIdHolder.Card_id)
                it.context.startActivity(intent)
                update_main_screen()
            }
        }

        init {
            binding.cardRemove.setOnClickListener {
                val position: Int = adapterPosition
                if (cardlist[position].list.isNotEmpty())
                {
                    val builder = AlertDialog.Builder(it.context)
                    builder.setTitle("List is not empty")
                    builder.setMessage("The list your trying to remove have som inn lists\n Do you want to remove it however")
                    builder.setPositiveButton("Yes") { _: DialogInterface, i: Int ->
                        cardlist.removeAt(position)
                        ref.child(auth.uid.toString()).setValue(cardlist)
                        update_main_screen()
                    }
                    builder.setNegativeButton("No") { _: DialogInterface, i: Int -> }
                    builder.show()
                }
                else{
                    cardlist.removeAt(position)
                    ref.child(auth.uid.toString()).setValue(cardlist)
                    update_main_screen()
                }

            }
        }

        init {
            binding.cardEdit.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(it.context, AddCardActivity::class.java)
                intent.putExtra("position", position)
                it.context.startActivity(intent)
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
        holder.bind(card, position)

    }

    override fun getItemCount(): Int {
        return cards.size
    }

}