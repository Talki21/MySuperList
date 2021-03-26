package com.example.mysuperlist

import android.util.Log
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

fun signInAnonymously(auth: FirebaseAuth, tag: String) {
    auth.signInAnonymously().addOnSuccessListener {
        Log.d(tag, "Login success ${it.user?.toString()}")
    }.addOnFailureListener {
        Log.e(tag, "Login failed", it)
    }
}

fun put_progress(int: Int) {
    cardlist.forEach {
        if (it.id == int) {
            var total = it.list.count()
            var checked = 0
            it.list.forEach { inncard ->
                if (inncard.check) {
                    checked++
                }
            }
            if (total != 0) {
                val prosent: Int = checked * 100 / total
                it.Progress = prosent
                total = 0
            } else {
                it.Progress = 0
            }
        }
    }
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
