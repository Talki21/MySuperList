package com.example.mysuperlist


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityAddinncardBinding

class AddInnCardActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAddinncardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddinncardBinding.inflate(layoutInflater)
        setContentView(binding.root)


         val position= intent.getIntExtra("position2",-1)
        if (position == -1){supportActionBar?.title = "Add New Inn List"}
        else{supportActionBar?.title = "Edit your Inn List"}

        binding.SaveNewInnCard.setOnClickListener {
            val title= binding.AddInnTitle.text.toString()
            val inn_card_new = inn_card(inn_id = cardIdHolder.Card_id,check = false,inn_title = title)
            println("aaaaaaa ************ add${inn_card_new}")
            cardlist.forEach {
                if (it.id == cardIdHolder.Card_id)
                {
                    if (position == -1){it.list.add(inn_card_new)}
                    else{it.list[position].inn_title = title}
                    put_progress(cardIdHolder.Card_id)
                    ref.child(auth.uid.toString()).setValue(cardlist)
                    finish()
                }
            }
        }
    }
}