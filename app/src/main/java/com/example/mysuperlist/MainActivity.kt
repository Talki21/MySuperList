package com.example.mysuperlist

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.Delegates


// å deklrere hoved listen her gir fleksibiletet til å endre eller addere hvor som helst
var cardlist = mutableListOf<card>()
val ref = FirebaseDatabase.getInstance().getReference("Users")
val auth = Firebase.auth

class cardIdHolder {
    companion object {
        var Card_id by Delegates.notNull<Int>()
    }
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // updata hoved skjerm
    fun update_main_screen() { // updata hoved skjerm
        binding.recycleFront.adapter = FrontRecycleAdapter(
            cardlist,
            this::onCardClicked,
            this::onCardRemoved,
            this::onCardEdit
        )
        binding.recycleFront.layoutManager = LinearLayoutManager(this)
    }

    private val tag: String = "My Super List M"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInAnonymously(auth, tag)

        if (supportActionBar != null) {
            this.supportActionBar?.hide()
        }

        val popupMenu = PopupMenu(this, night_dark)
        popupMenu.menu.add(Menu.NONE, 0, 0, "Night Mode")
        popupMenu.menu.add(Menu.NONE, 1, 1, "Light Mode")
        popupMenu.setOnMenuItemClickListener {
            val id = it.itemId
            if (id == 0) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            false
        }
        night_dark.setOnClickListener {
            popupMenu.show()
        }

        upload()

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }

        update_main_screen()
    }

    override fun onStart() {
        update_main_screen()
        super.onStart()
    }

    override fun onBackPressed() {
    }

    private fun onCardClicked(position: Int): Unit {
        val intent = Intent(this, SecandActivity::class.java)
        cardIdHolder.Card_id = cardlist[position].id
        intent.putExtra("id", cardIdHolder.Card_id)
        startActivity(intent)
    }

    private fun onCardRemoved(position: Int): Unit {
        if (cardlist[position].list.isNotEmpty()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("List is not empty")
            builder.setMessage("The list your trying to remove have som inn lists\n Do you want to remove it however")
            builder.setPositiveButton("Yes") { _: DialogInterface, i: Int ->
                cardlist.removeAt(position)
                ref.child(auth.uid.toString()).setValue(cardlist)
                update_main_screen()
            }
            builder.setNegativeButton("No") { _: DialogInterface, i: Int -> }
            builder.show()
        } else {
            cardlist.removeAt(position)
            ref.child(auth.uid.toString()).setValue(cardlist)
            update_main_screen()
        }
    }

    private fun onCardEdit(position: Int): Unit {
        val intent = Intent(this, AddCardActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    fun upload() {
        val get = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                cardlist.clear()
                p0.children.forEach {
                    val id = it.child("id").value.toString().toInt()
                    val tit = it.child("title").value.toString()
                    val pro = it.child("progress").value.toString().toInt()
                    val list = it.child("list")
                    val inn_card_list = mutableListOf<inn_card>()
                    if (list.children.count() != 0) {
                        list.children.forEach { d ->
                            val inn_tit = d.child("inn_title").value.toString()
                            val inn_id = d.child("inn_id").value.toString().toInt()
                            val check = d.child("check").value.toString().toBoolean()
                            val inn_card = inn_card(inn_id, check, inn_tit)
                            inn_card_list.add(inn_card)
                        }
                    }
                    val Card = card(id, tit, pro, inn_card_list)
                    cardlist.add(Card)
                }
                update_main_screen()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }
        ref.child(auth.uid.toString()).addValueEventListener(get)
        ref.child(auth.uid.toString()).addListenerForSingleValueEvent(get)
    }


}






