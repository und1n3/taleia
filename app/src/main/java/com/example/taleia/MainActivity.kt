package com.example.taleia

import android.app.Activity
import com.example.taleia.R
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.taleia.EscenaActivity
import com.example.taleia.PersonatgeActivity
import com.example.taleia.FraseActivity
import com.example.taleia.InfoActivity
import com.example.taleia.SettingsActivity
import com.example.taleia.ui.login.LoginActivity
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.drawerlayout.widget.DrawerLayout
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taleia.ui.login.LoginViewModel
import com.google.android.material.navigation.NavigationView
import org.json.JSONObject
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.appwrite.Client
import io.appwrite.services.Account

class MainActivity : AppCompatActivity() {
    lateinit var toggle : ActionBarDrawerToggle
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.create(this)
        mainViewModel.getCurrentAccount()
        mainViewModel._session.observe(this, Observer() { loginStatus ->
                if (loginStatus != null) {
                    showMenuItems()
                }else{
                    hideMenuItems()
                }
        })

        val drawerLayout : DrawerLayout = findViewById(R.id.drawer)
        val navView: NavigationView = findViewById(R.id.navigation_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_saved_prompts -> {
                    val i = Intent(this, SavedPromptsActivity::class.java)

                    startActivity(i)
                }
                R.id.menu_settings -> Toast.makeText(applicationContext, "Android Menu is Clicked", Toast.LENGTH_LONG).show()
                R.id.log_out_button -> {
                    Toast.makeText(applicationContext, "Android login out Clicked", Toast.LENGTH_LONG).show()
                    mainViewModel.logOut()
                }
                R.id.log_in_button -> {
                    val i = Intent(this, LoginActivity::class.java)
                    startActivity(i)
                }
            }
            true
        }


    }

    override fun onResume() {
        mainViewModel.create(this)
        mainViewModel.getCurrentAccount()
        super.onResume()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
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
    fun showMenuItems():Unit {
        val navigationView : NavigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.getMenu().findItem(R.id.log_in_button).setVisible(false)
        navigationView.getMenu().findItem(R.id.menu_saved_prompts).setVisible(true)
        navigationView.getMenu().findItem(R.id.menu_settings).setVisible(true)
        navigationView.getMenu().findItem(R.id.log_out_button).setVisible(true)
    }

    fun hideMenuItems():Unit {
        val navigationView : NavigationView = findViewById<NavigationView>(R.id.navigation_view)
        navigationView.getMenu().findItem(R.id.log_in_button).setVisible(true)
        navigationView.getMenu().findItem(R.id.menu_saved_prompts).setVisible(false)
        navigationView.getMenu().findItem(R.id.menu_settings).setVisible(false)
        navigationView.getMenu().findItem(R.id.log_out_button).setVisible(false)
    }



}