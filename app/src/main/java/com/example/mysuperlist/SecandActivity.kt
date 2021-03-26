package com.example.mysuperlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivitySecandBinding
import com.example.mysuperlist.databinding.ItemInnLayoutBinding


private lateinit var binding: ActivitySecandBinding
fun update_secand_screen() {
    val inn_card_show = mutableListOf<inn_card>()
    cardlist.forEach {
        if (it.id == cardIdHolder.Card_id) {
            it.list.forEach { x ->
                inn_card_show.add(x)
            }
            binding.cardTitleTop.text = it.Title
            binding.progressBarTop.progress = it.Progress
            binding.prosentInn.text = "${it.Progress}%"
        }
    }

    binding.recycleSecand.adapter = SecandRecycleAdapter(inn_card_show)
    binding.recycleSecand.layoutManager = LinearLayoutManager(SecandActivity())
}

class SecandActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            this.supportActionBar?.hide()
        }


        binding.floatingActionButtonInn.setOnClickListener {
            val intent = Intent(this, AddInnCardActivity::class.java)
            startActivity(intent)
        }
        update_secand_screen()
    }

    override fun onStart() {
        update_secand_screen()
        super.onStart()
    }

    override fun onBackPressed() {
        put_progress(cardIdHolder.Card_id)
        finish()
    }
}