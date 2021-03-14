package com.example.mysuperlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityAddcardBinding
import com.example.mysuperlist.databinding.ActivityAddinncardBinding

class AddInnCardActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddinncardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddinncardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Add New Inn List"

        binding.SaveNewInnCard.setOnClickListener {
            val title= binding.AddInnTitle.text.toString()
            val id2 = intent.getIntExtra("id2",0)
            inn_cardList.add(inn_card(id2,false,title))
            finish()
        }

    }


}