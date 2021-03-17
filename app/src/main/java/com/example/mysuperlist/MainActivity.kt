package com.example.mysuperlist
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mysuperlist.data.card
import com.example.mysuperlist.data.inn_card
import com.example.mysuperlist.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Path


// å sette den her gir meg mulighet til å lage funksjon på utside av MainActivity for å updatere skjermen
private lateinit var binding: ActivityMainBinding
var cardlist = mutableListOf<card>() // å deklrere hoved listen her gir fleksibiletet til å endre eller addere hvor som helst

fun update_main_screen(Main : MainActivity){ // updata hoved skjerm
    binding.recycleFront.adapter = frontRecycleAdapter(cardlist)
    binding.recycleFront.layoutManager = LinearLayoutManager(Main)

}
var path: File? = null
class MainActivity : AppCompatActivity() {
    private val tag:String ="My Super List M"
    private lateinit var auth: FirebaseAuth

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         auth = Firebase.auth
         signInAnonymously(auth,tag)
         if (supportActionBar!=null) { this.supportActionBar?.hide() }



        binding.floatingActionButton.setOnClickListener{
            val intent = Intent(this,AddCardActivity::class.java)
            startActivity(intent)
        }
         update_main_screen(this)

         path = this.baseContext.getExternalFilesDir(null)
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
    save(cardlist, path)
    update_secand_screen(SecandActivity(),int)
    update_main_screen(MainActivity())
}

fun save(list: MutableList<card>,path:File?){
    if (path!=null)
    {
        if (File(path,"Cardlist.txt").exists()){File(path,"Cardlist.txt").delete()}
        val file = File(path,"Cardlist.txt")
        FileOutputStream(file, true).bufferedWriter().use { writer ->
            list.forEach {
                writer.write("${it.toString()}*****************************\n")
            }
            upload(file.toUri(),"MySuperLit")
        }
    }
}

private fun signInAnonymously(auth:FirebaseAuth,tag:String){
    auth.signInAnonymously().addOnSuccessListener {
        Log.d(tag,"Login success ${it.user.toString()}")
    }.addOnFailureListener{
        Log.e(tag,"Login failed",it)
    }
}

private fun upload(file: Uri,tag: String){
    Log.d(tag, "upload file $file")
    val ref = FirebaseStorage.getInstance().reference.child("lists/${file.lastPathSegment}")
    val uploadTask = ref.putFile(file)

    uploadTask.addOnSuccessListener {
        Log.d(tag,"Saved file to firebase ${it.toString()}")
    }.addOnFailureListener{
        Log.e(tag,"Error saving file to firebase",it)
    }
}