package com.example.mysuperlist

import android.util.Log
import android.view.Menu
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatDelegate
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

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
