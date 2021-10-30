package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.taleia.R
import java.util.*

class PersonatgeActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    var tick1Actual : String = ""
    var tick2Actual : String = ""
    var adjectiuActual : String =""
    var personatgeActual : String =""

    var collectionId: String = "617d085674b62"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personatge)
        val ocupacio = findViewById<Button>(R.id.ocupacio_boto) // buscar botons del layout
        val adjectiu = findViewById<Button>(R.id.adjectius_boto) // hi posem final pq hi accedim a dins d classes
        val accions = findViewById<Button>(R.id.accions_boto)
        val mania = findViewById<Button>(R.id.manies_boto)
        val res = resources //agafa llista del fitxer
        val ocupacions = res.getStringArray(R.array.personatges)
        val adj = res.getStringArray(R.array.adjectius)
        val acc = res.getStringArray(R.array.acció)
        val man = res.getStringArray(R.array.paraules)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.create(this)
        mainViewModel.getCurrentAccount()


        ocupacio.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(150) //sera de 0 a 9
            ocupacio.text = ocupacions[n]
            personatgeActual = ocupacions[n]
        }
        adjectiu.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(421) //sera de 0 a 9
            adjectiu.text = adj[n]
            adjectiuActual = adj[n]
        }
        accions.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(427) //sera de 0 a 9
            accions.text = acc[n]
            tick1Actual = acc[n]
        }
        mania.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(574) //sera de 0 a 9
            mania.text = man[n]
            tick2Actual = man[n]
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

            if (adjectiuActual != "" && tick1Actual != "" && tick2Actual != "" && personatgeActual != "") {
                //Save the words to Appwrite for user
                mainViewModel.saveCharacter(this,personatgeActual,adjectiuActual,tick1Actual,tick2Actual,collectionId)
            }


        } else return super.onOptionsItemSelected(item)
        return true
    }
}