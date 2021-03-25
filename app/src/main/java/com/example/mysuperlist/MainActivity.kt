package com.example.mysuperlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
private lateinit var binding: ActivityMainBinding

// updata hoved skjerm
fun update_main_screen(){ // updata hoved skjerm
    binding.recycleFront.adapter = FrontRecycleAdapter(cardlist)
    binding.recycleFront.layoutManager = LinearLayoutManager(MainActivity())
}
class MainActivity : AppCompatActivity() {


    private val tag: String = "My Super List M"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signInAnonymously(auth, tag)
        if (supportActionBar != null) {
            this.supportActionBar?.hide()
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

}






