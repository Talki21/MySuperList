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

        supportActionBar?.title = "Add New List"

        binding.SaveNewCard.setOnClickListener {
           val title= binding.AddTitle.text.toString()
            val inn_card_show  = mutableListOf<inn_card>()
            cardlist.add(card(Title = title,list = inn_card_show))
            println("1111111***${cardlist}")
            finish()
        }

    }


}