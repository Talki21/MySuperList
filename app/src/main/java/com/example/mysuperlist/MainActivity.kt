package com.example.mysuperlist
import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

import java.io.File
import java.io.FileOutputStream
import java.util.*


// å sette den her gir meg mulighet til å lage funksjon på utside av MainActivity for å updatere skjermen
private lateinit var binding: ActivityMainBinding
var cardlist = mutableListOf<card>() // å deklrere hoved listen her gir fleksibiletet til å endre eller addere hvor som helst
val ref = FirebaseDatabase.getInstance().getReference("lists")



fun update_main_screen(Main : MainActivity){ // updata hoved skjerm
    binding.recycleFront.adapter = frontRecycleAdapter(cardlist)
    binding.recycleFront.layoutManager = LinearLayoutManager(Main)
}


class MainActivity : AppCompatActivity() {

    private val tag:String ="My Super List M"
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
         update_main_screen(MainActivity())
         val auth = Firebase.auth
         signInAnonymously(auth,tag)
         upload()

         if (supportActionBar!=null) { this.supportActionBar?.hide() }

         binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this,AddCardActivity::class.java)
            startActivity(intent)
         }
         update_main_screen(MainActivity())
    }

    override fun onBackPressed() {
    update_main_screen(MainActivity())
    }
}
fun put_progress(int: Int){
    cardlist.forEach {
        if (it.id == int)
        {
            var total = it.list.count()
            var checked = 0
            it.list.forEach { inncard ->
                if (inncard.check) {
                    checked++
                }
            }
            if (total!=0){
                val prosent:Int = checked*100/ total
                it.Progress = prosent
                total = 0
            }
            else
            {
                it.Progress = 0
            }

        }
    }
    update_secand_screen(SecandActivity(),int)
    update_main_screen(MainActivity())
}

private fun signInAnonymously(auth: FirebaseAuth, tag:String){
    auth.signInAnonymously().addOnSuccessListener {
        Log.d(tag,"Login success ${it.user?.toString()}")
    }.addOnFailureListener{
        Log.e(tag,"Login failed",it)
    }
}

fun upload(){
    cardlist.clear()
    val get = object : ValueEventListener {
        override fun onDataChange(p0: DataSnapshot) {
            p0.children.forEach {
                val id = it.child("id").value.toString().toInt()
                val tit = it.child("title").value.toString()
                val pro = it.child("progress").value.toString().toInt()
                val list =it.child("list")
                val inn_card_list  = mutableListOf<inn_card>()
                if (list.children.count()!=0) {
                    list.children.forEach { d ->
                        val inn_tit = d.child("inn_title").value.toString()
                        val inn_id = d.child("inn_id").value.toString().toInt()
                        val check = d.child("check").value.toString().toBoolean()
                        val inn_card = inn_card(inn_id,check,inn_tit)
                        inn_card_list.add(inn_card)
                    }
                }
                val Card = card(id,tit,pro,inn_card_list)
                cardlist.add(Card)
            }
        }
        override fun onCancelled(error: DatabaseError) {
        }
    }
    ref.addListenerForSingleValueEvent(get)
}






