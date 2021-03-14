package com.example.mysuperlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ItemLayoutBinding




class frontRecycleAdapter(public var cards: MutableList<card>):RecyclerView.Adapter<frontRecycleAdapter.ViewHolder>()
{
   class ViewHolder (val binding:ItemLayoutBinding ) : RecyclerView.ViewHolder(binding.root){
       fun bind(card: card) {
            binding.cardTitle.text = card.Title
            binding.progressBar.progress = card.Progress
       }
       fun new(this_Ac:MainActivity){
           itemView.setOnClickListener {
               val position: Int = adapterPosition
               val intent = Intent(it.context,SecandActivity::class.java)
               intent.putExtra("cardtitle", cardlist[position].Title)
               intent.putExtra("id", cardlist[position].id)
               it.context.startActivity(intent)
           }
       }
       init {
           binding.cardRemove.setOnClickListener {
               val position: Int = adapterPosition
               cardlist.removeAt(position)
               inn_cardList.removeAll { innCard: inn_card ->innCard.id == cardlist[position].id  }
               update_main_screen(MainActivity())

           }
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
        holder.new(MainActivity())
    }

    override fun getItemCount(): Int {
        return cards.size
    }

}