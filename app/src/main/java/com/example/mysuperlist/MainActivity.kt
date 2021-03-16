package com.example.mysuperlist
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityMainBinding

// å sette den her gir meg mulighet til å lage funksjon på utside av MainActivity for å updatere skjermen
private lateinit var binding: ActivityMainBinding
var cardlist = mutableListOf<card>() // å deklrere hoved listen her gir fleksibiletet til å endre eller addere hvor som helst

fun update_main_screen(Main : MainActivity){ // updata hoved skjerm
    binding.recycleFront.adapter = frontRecycleAdapter(cardlist)
    binding.recycleFront.layoutManager = LinearLayoutManager(Main)

}

class MainActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         if (supportActionBar!=null) { this.supportActionBar?.hide() }

        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this,AddCardActivity::class.java)
            startActivity(intent)
        }
         update_main_screen(this)
    }
    override fun onBackPressed() {

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