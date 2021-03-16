package com.example.mysuperlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ItemLayoutBinding



var card_id:Int = 0
class frontRecycleAdapter(public var cards: MutableList<card>):RecyclerView.Adapter<frontRecycleAdapter.ViewHolder>()
{
   class ViewHolder (val binding:ItemLayoutBinding ) : RecyclerView.ViewHolder(binding.root){
       fun bind(card: card) {
            binding.cardTitle.text = card.Title
            binding.progressBar.progress = card.Progress
            binding.prosent.text = "${card.Progress}%"
       }
       fun new(this_Ac:MainActivity){
           itemView.setOnClickListener {
               val position: Int = adapterPosition
               val intent = Intent(it.context,SecandActivity::class.java)
               card_id = cardlist[position].id
               intent.putExtra("id", card_id)
               it.context.startActivity(intent)
               update_main_screen(MainActivity())
           }
       }
       init {
           binding.cardRemove.setOnClickListener {
               val position: Int = adapterPosition
               cardlist.removeAt(position)
               save(cardlist, path)
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