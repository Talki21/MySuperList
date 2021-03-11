package com.example.mysuperlist

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mysuperlist.data.card
import com.example.mysuperlist.databinding.ActivityMainBinding
import com.example.mysuperlist.databinding.ItemLayoutBinding
import com.example.mysuperlist.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class frontRecycleAdapter(public var cards:List<card>):RecyclerView.Adapter<frontRecycleAdapter.ViewHolder>()
{
   class ViewHolder (val binding:ItemLayoutBinding ) : RecyclerView.ViewHolder(binding.root){
       fun bind(card: card) {
            binding.cardTitle.text = card.Title
            binding.progressBar.progress = card.Progress.toInt()
       }
       init {
           itemView.setOnClickListener {
               val position: Int = adapterPosition
              println("you clicked on card ${position+1}")
           }
       }
       init {
           binding.cardRemove.setOnClickListener {
               val position: Int = adapterPosition
               cardlist.removeAt(position)
               update_screen(MainActivity())

           }
       }


   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

}