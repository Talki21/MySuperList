package com.example.mysuperlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivitySecandBinding
import com.example.mysuperlist.databinding.ItemInnLayoutBinding




class SecandActivity : AppCompatActivity() {
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

        binding.recycleSecand.adapter = SecandRecycleAdapter(inn_card_show,
            this::onInnCardChecked,
            this::onInnCardRemoved,
            this::onInnCardEdit)
        binding.recycleSecand.layoutManager = LinearLayoutManager(SecandActivity())
    }


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

    private fun onInnCardChecked(position:Int): Unit{
        val binding2: ItemInnLayoutBinding
        binding2 = ItemInnLayoutBinding.inflate(LayoutInflater.from(this))
        cardlist.forEach {
            if (it.id == cardIdHolder.Card_id) {
                if (it.list[position].check) {

                    binding2.checkBox.isChecked = false
                    it.list[position].check = false
                } else {
                    binding2.checkBox.isChecked = true
                    it.list[position].check = true
                }
                put_progress(cardIdHolder.Card_id)
            }
        }
        ref.child(auth.uid.toString()).setValue(cardlist)
        update_secand_screen()

    }

    private fun onInnCardRemoved(position: Int): Unit{
        cardlist.forEach {
            if (it.id == cardIdHolder.Card_id) {
                it.list.removeAt(position)
                put_progress(cardIdHolder.Card_id)
            }
        }
        ref.child(auth.uid.toString()).setValue(cardlist)
        update_secand_screen()

    }

    private fun onInnCardEdit(position: Int): Unit{
        val intent = Intent(this, AddInnCardActivity::class.java)
        intent.putExtra("position2", position)
        startActivity(intent)
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