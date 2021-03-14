package com.example.mysuperlist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivitySecandBinding


var inn_cardList = mutableListOf<inn_card>()
var inn_card_show  = mutableListOf<inn_card>()
private lateinit var binding: ActivitySecandBinding

fun update_secand_screen(con : SecandActivity){
    binding.recycleSecand.adapter = secandRecycleAdapter(inn_card_show)
    binding.recycleSecand.layoutManager = LinearLayoutManager(con)

}


class SecandActivity() : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecandBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val top_title = intent.getStringExtra("cardtitle")
        val id = intent.getIntExtra("id",0)
        supportActionBar?.title = top_title + id.toString()

        binding.floatingActionButtonInn.setOnClickListener{
            val intent = Intent(this,AddInnCardActivity::class.java)
            intent.putExtra("id2",id)
            startActivity(intent)
        }
        inn_card_show.clear()
        inn_cardList.forEach {
            if (it.id == id){
                inn_card_show.add(it)
            }
        }
        update_secand_screen(this)

    }
}