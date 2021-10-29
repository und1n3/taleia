package com.example.taleia.ui.login

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import com.example.taleia.R

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        loginViewModel.create(this)


        val mail = findViewById<EditText>(R.id.mail_address)

        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        login.setOnClickListener {
            loginViewModel.login(this, mail.text.toString(),password.text.toString())
        }

        loginViewModel.user.observe(this) { user ->
            if (user != null) {
                Toast.makeText(this, "Hello: " + user.getString("name") + "!", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }
        loginViewModel.errorMessage.observe(this) { errorMessage ->
            if (errorMessage == "Invalid credentials") {
                loginViewModel.displayNamePrompt(this,mail.text.toString(), password.text.toString())
            }
            else if (errorMessage!= null) {
                Toast.makeText(this,"Error: "+errorMessage, Toast.LENGTH_SHORT).show()
            }

        }




        }
    }



