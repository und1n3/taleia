package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.taleia.R
import java.util.*

class PersonatgeActivity : AppCompatActivity() {
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
        ocupacio.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(150) //sera de 0 a 9
            ocupacio.text = ocupacions[n]
        }
        adjectiu.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(421) //sera de 0 a 9
            adjectiu.text = adj[n]
        }
        accions.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(427) //sera de 0 a 9
            accions.text = acc[n]
        }
        mania.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(574) //sera de 0 a 9
            mania.text = man[n]
        }
    }
}