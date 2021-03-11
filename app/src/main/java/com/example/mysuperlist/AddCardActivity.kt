package com.example.mysuperlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mysuperlist.data.card
import com.example.mysuperlist.databinding.ActivityAddcardBinding
import com.example.mysuperlist.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_addcard.view.*

class AddCardActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddcardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SaveNewCard.setOnClickListener {
           var title= binding.AddTitle.text.toString()
            cardlist.add(card(title))
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }


}