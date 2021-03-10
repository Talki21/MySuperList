package com.example.mysuperlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.card
import com.example.mysuperlist.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var cardlist = mutableListOf<card>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addsomecard()

        recycle_front.layoutManager = LinearLayoutManager(this)
        recycle_front.adapter = frontRecycleAdapter(cardlist.sortedBy { card -> card.Title })
    }

    private fun addsomecard() {
        for (i in 1..25){
            cardlist.add(card("title${i}",i*2))
        }
    }
}