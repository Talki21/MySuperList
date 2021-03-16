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
        var index:Int =0
        binding.SaveNewInnCard.setOnClickListener {
            val title= binding.AddInnTitle.text.toString()
            val id2 = intent.getIntExtra("id2",0)
            val inn_card_show:inn_card = inn_card(inn_id = id2,check = false,inn_title = title)
            cardlist.forEach {
                if (it.id == id2)
                {
                    it.list.add(inn_card_show)
                }
            }
            put_progress(id2)
            finish()
        }

    }


}