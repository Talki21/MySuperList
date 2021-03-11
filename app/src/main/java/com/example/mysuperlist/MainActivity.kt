package com.example.mysuperlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.card
import com.example.mysuperlist.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import com.example.mysuperlist.AddCardActivity

private lateinit var binding: ActivityMainBinding
public var cardlist = mutableListOf<card>()

public fun update_screen(con : MainActivity){
    binding.recycleFront.layoutManager = LinearLayoutManager(con)
    binding.recycleFront.adapter = frontRecycleAdapter(cardlist.sortedBy { card -> card.Title })
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this,AddCardActivity::class.java)
            startActivity(intent)
        }
        update_screen(this)
    }


}