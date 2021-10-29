package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.taleia.R
import java.util.*

class FraseActivity : AppCompatActivity() {
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
        prompt.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(574) //sera de 0 a 9
            prompt.text = prompts[n]
        }
        estil.setOnClickListener { //aquí el codi pel boto
            val rand = Random() //agafem num aleatori
            val n = rand.nextInt(23) //sera de 0 a 9
            estil.text = style[n]
        }
        restriccio.setOnClickListener {
            val rand = Random()
            val n = rand.nextInt(76)
            restriccio.text = restr[n]
        }
    }

}