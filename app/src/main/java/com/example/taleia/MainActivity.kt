package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taleia.R
import android.content.Intent
import android.view.View
import com.example.taleia.EscenaActivity
import com.example.taleia.PersonatgeActivity
import com.example.taleia.FraseActivity
import com.example.taleia.InfoActivity
import com.example.taleia.SettingsActivity
import com.example.taleia.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openEscenaActivity(view: View?) {
        val i = Intent(this, EscenaActivity::class.java)
        startActivity(i)
    }

    fun openPersonatgeActivity(view: View?) {
        val i = Intent(this, PersonatgeActivity::class.java)
        startActivity(i)
    }

    fun openFraseActivity(view: View?) {
        val i = Intent(this, FraseActivity::class.java)
        startActivity(i)
    }

    fun openInfoActivity(view: View?) {
        val i = Intent(this, InfoActivity::class.java)
        startActivity(i)
    }

    fun openSettingsActivity(view: View?) {
        val i = Intent(this, SettingsActivity::class.java)
        startActivity(i)
    }

    fun openLoginActivity(view: View?) {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
    }
}