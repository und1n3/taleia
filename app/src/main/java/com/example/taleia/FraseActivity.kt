package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.taleia.R
import java.util.*

class FraseActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    var paraulaActual : String = ""
    var estilActual : String =""
    var restriccioActual : String = ""
    var collectionId: String = "617d085e2e980"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frase)
        val prompt = findViewById<Button>(R.id.paraula_boto) // buscar botons del layout
        val estil = findViewById<Button>(R.id.estil_boto) // hi posem final pq hi accedim a dins d classes
        val restriccio = findViewById<Button>(R.id.restriccio_boto)
        val res = resources //agafa llista del fitxer
        val prompts = res.getStringArray(R.array.paraules) //
        val style = res.getStringArray(R.array.estils) //
        val restr = res.getStringArray(R.array.restricc)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.create(this)
        mainViewModel.getCurrentAccount()


        prompt.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(574) //sera de 0 a 9
            prompt.text = prompts[n]
            paraulaActual = prompts[n]
        }
        estil.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(23) //sera de 0 a 9
            estil.text = style[n]
            estilActual = style[n]
        }
        restriccio.setOnClickListener {
            val rand = Random()
            val n = rand.nextInt(76)
            restriccio.text = restr[n]
            restriccioActual = restr[n]
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.save_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id: Int = item.getItemId()
        if (id == R.id.action_save) {
            //process your onClick here

            if (paraulaActual != "" && estilActual != "" && restriccioActual != "") {
                //Save the words to Appwrite for user
                mainViewModel.saveChallenge(this,paraulaActual,estilActual,restriccioActual,collectionId)
            }


        } else return super.onOptionsItemSelected(item)
        return true
    }
}