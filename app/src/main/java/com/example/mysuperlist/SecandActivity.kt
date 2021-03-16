package com.example.mysuperlist

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperl.secandRecycleAdapter
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivitySecandBinding




private lateinit var binding: ActivitySecandBinding

fun update_secand_screen(con : SecandActivity,id:Int){
    val inn_card_show  = mutableListOf<inn_card>()
    cardlist.forEach {
        if (it.id == id)
        {
            it.list.forEach { x ->
                inn_card_show.add(x)
            }
        }
    }
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
        update_secand_screen(this, id)
    }

    override fun onBackPressed() {
        put_progress(card_id)
        finish()
    }
}