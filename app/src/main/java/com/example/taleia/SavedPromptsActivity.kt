package com.example.taleia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SavedPromptsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_prompts)

        val name = intent.getStringExtra("nameUser")
        val mail = intent.getStringExtra("mailUser")


        intent.putExtra("nameUser",name)
        intent.putExtra("mailUser",mail)
        setResult(-1,intent)
//        val intentResult = getIntent()
//        intentResult.putExtra("nameUser",name)
//        intentResult.putExtra("mailUser",mail)
//
//        setResult(-1,intentResult)
//        finish()
    }
}