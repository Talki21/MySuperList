package com.example.mysuperlist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityAddcardBinding
import kotlinx.android.synthetic.main.activity_addcard.*

class AddCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddcardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", -1)
        if (position == -1) {
            supportActionBar?.title = "Add New List"
        } else {
            supportActionBar?.title = "Edit your List"
        }
        AddTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (AddTitle.text.isNotEmpty()) {
                    SaveNewCard.isEnabled = true
                } else {
                    SaveNewCard.isEnabled = false
                    AddTitle.error = "Title can not be empty"
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.SaveNewCard.setOnClickListener {
            val title = binding.AddTitle.text.toString()
            var titleExisted: Boolean = false
            cardlist.forEach {
                if (it.Title == title) {
                    titleExisted = true
                }
            }
            if (titleExisted) {
                AddTitle.error = "you have a list with same title"
            } else {
                val inn_card_show = mutableListOf<inn_card>()
                if (position == -1) {
                    val newCard = card(Title = title, list = inn_card_show)
                    cardlist.add(newCard)
                } else {
                    cardlist[position].Title = title
                }
                ref.child(auth.uid.toString()).setValue(cardlist)
                finish()
            }


        }
    }
}