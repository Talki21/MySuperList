package com.example.mysuperlist


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityAddinncardBinding
import kotlinx.android.synthetic.main.activity_addcard.*
import kotlinx.android.synthetic.main.activity_addcard.AddTitle
import kotlinx.android.synthetic.main.activity_addinncard.*

class AddInnCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddinncardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddinncardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val position = intent.getIntExtra("position2", -1)
        if (position == -1) {
            supportActionBar?.title = "Add new item"
        } else {
            supportActionBar?.title = "Edit your item name"
        }

        AddInnTitle.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (AddInnTitle.text.isNotEmpty()) {
                    SaveNewInnCard.isEnabled = true
                } else {
                    SaveNewInnCard.isEnabled = false
                    AddInnTitle.error = "Title can not be empty"
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        binding.SaveNewInnCard.setOnClickListener {
            val title = binding.AddInnTitle.text.toString()
            var titleExisted: Boolean = false
            cardlist.forEach {
                if (it.id == cardIdHolder.Card_id) {
                    it.list.forEach { inn ->
                        if (inn.inn_title == title) {
                            titleExisted = true
                        }
                    }
                }
            }
            if (titleExisted) {
                AddInnTitle.error = "You already have an item with this name"
            }
            else {
                val inn_card_new =
                    inn_card(inn_id = cardIdHolder.Card_id, check = false, inn_title = title)
                cardlist.forEach {
                    if (it.id == cardIdHolder.Card_id) {
                        if (position == -1) {
                            it.list.add(inn_card_new)
                        } else {
                            it.list[position].inn_title = title
                        }
                        put_progress(cardIdHolder.Card_id)
                        ref.child(auth.uid.toString()).setValue(cardlist)
                        finish()
                    }
                }
            }
        }
    }
}