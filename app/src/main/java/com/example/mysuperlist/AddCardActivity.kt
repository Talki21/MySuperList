package com.example.mysuperlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityAddcardBinding

class AddCardActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddcardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddcardBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val position = intent.getIntExtra("position",-1)
        if (position == -1){supportActionBar?.title = "Add New List"}
        else{supportActionBar?.title = "Edit your List"}
        binding.SaveNewCard.setOnClickListener {
           val title= binding.AddTitle.text.toString()
            val inn_card_show  = mutableListOf<inn_card>()
            if (position == -1){

                val newCard = card(Title = title,list = inn_card_show)
                cardlist.add(newCard)
            }
            else{
                cardlist[position].Title = title
            }
            ref.child(auth.uid.toString()).setValue(cardlist)
            finish()
        }
    }
}