package com.example.taleia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taleia.R
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.taleia.EscenaActivity
import com.example.taleia.PersonatgeActivity
import com.example.taleia.FraseActivity
import com.example.taleia.InfoActivity
import com.example.taleia.SettingsActivity
import com.example.taleia.ui.login.LoginActivity
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.drawerlayout.widget.DrawerLayout
import androidx.annotation.NonNull
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {
    var drawerLayout: DrawerLayout? = null
    var actionBarDrawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        drawerLayout = findViewById(R.id.drawer)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout?.addDrawerListener(actionBarDrawerToggle!!)
        actionBarDrawerToggle!!.syncState()

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            when (item.itemId) {
                R.id.menu_saved_prompts -> {
                    Toast.makeText(this, "Android prompts is Clicked", Toast.LENGTH_LONG).show()

                    val i = Intent(this, SavedPromptsActivity::class.java)
                    this.startActivity(i)
                }
                R.id.menu_settings -> {
//                    val i = Intent(this, UserSettingsActivity::class.java)
//                    this.startActivity(i)
                    Toast.makeText(this, "Android Menu is Clicked", Toast.LENGTH_LONG).show()
                }
                R.id.log_out_button -> {
//                    val i = Intent(this, LogOutActivity::class.java)
//                    this.startActivity(i)
                    Toast.makeText(this, "Android Logout is Clicked", Toast.LENGTH_LONG).show()

                }
                R.id.log_in_button -> {
                    Toast.makeText(this, "Android login is Clicked", Toast.LENGTH_LONG).show()

                    val i = Intent(this, LoginActivity::class.java)
                    this.startActivity(i)
                }

                else -> return super.onOptionsItemSelected(item)
            }
            return true
        }else return super.onOptionsItemSelected(item)
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
    fun hideMenuItems():Unit {
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.getMenu().findItem(R.id.log_in_button).setVisible(false)
        navigationView.getMenu().findItem(R.id.menu_saved_prompts).setVisible(true)
        navigationView.getMenu().findItem(R.id.menu_settings).setVisible(true)
        navigationView.getMenu().findItem(R.id.log_out_button).setVisible(true)
    }
//
//    fun openLoginActivity(view: View?) {
//        val i = Intent(this, LoginActivity::class.java)
//        startActivity(i)
//    }

}