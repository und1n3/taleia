package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.taleia.R
import java.util.*

class EscenaActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    var paraulaActual : String = ""
    var personatgeActual : String =""
    var escenaActual : String = ""
    var collectionId: String = "617d0845ca7be"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escena)

        val paraula = findViewById<Button>(R.id.paraula_boto) // buscar botons del layout
        val escenari = findViewById<Button>(R.id.escenari_boto) // hi posem final pq hi accedim a dins d classes
        val personatge = findViewById<Button>(R.id.personatge_boto)
        val res = resources //agafa llista del fitxer
        val mots = res.getStringArray(R.array.paraules)
        val escena = res.getStringArray(R.array.escenes)
        val protagonista = res.getStringArray(R.array.personatges)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.create(this)
        mainViewModel.getCurrentAccount()


        paraula.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(574)
            paraula.text = mots[n]
            paraulaActual = mots[n]
        }
        escenari.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(108) //sera de 0 a 9
            escenari.text = escena[n]
            escenaActual=escena[n]
        }
        personatge.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(150) //sera de 0 a 9
            personatge.text = protagonista[n]
            personatgeActual = protagonista[n]
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

            if (paraulaActual != "" && escenaActual != "" && personatgeActual != "") {
                //Save the words to Appwrite for user
                mainViewModel.saveScene(this,paraulaActual,escenaActual,personatgeActual,collectionId)
            }


        } else return super.onOptionsItemSelected(item)
        return true
    }
}