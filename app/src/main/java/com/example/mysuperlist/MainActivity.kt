package com.example.mysuperlist
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.card
import com.example.mysuperlist.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

var cardlist = mutableListOf<card>()

fun update_main_screen(con : MainActivity){
    binding.recycleFront.adapter = frontRecycleAdapter(cardlist)
    binding.recycleFront.layoutManager = LinearLayoutManager(con)

}

class MainActivity : AppCompatActivity() {

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         supportActionBar?.title = "Mega List"

        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this,AddCardActivity::class.java)
            startActivity(intent)
        }
         update_main_screen(this)
    }


}