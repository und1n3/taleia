package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.taleia.R
import java.util.*

class EscenaActivity : AppCompatActivity() {
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


        val name = intent.getStringExtra("nameUser")
        val mail = intent.getStringExtra("mailUser")


        paraula.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(574)
            paraula.text = mots[n]
        }
        escenari.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(108) //sera de 0 a 9
            escenari.text = escena[n]
        }
        personatge.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(150) //sera de 0 a 9
            personatge.text = protagonista[n]
        }
    }
}