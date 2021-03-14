package com.example.mysuperlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mysuperlist.data.card
import com.example.mysuperlist.databinding.ActivityAddcardBinding

class AddCardActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddcardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Add New List"

        binding.SaveNewCard.setOnClickListener {
           var title= binding.AddTitle.text.toString()
            cardlist.add(card(Title = title))
            finish()
        }

    }


}