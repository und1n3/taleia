package com.example.taleia.ui.login

import android.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.taleia.data.LoginRepository
import com.example.taleia.data.Result
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import io.appwrite.Client
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.Account
import kotlinx.coroutines.launch
import org.json.JSONObject
import com.example.taleia.R

import android.view.LayoutInflater
import android.view.View
import android.content.DialogInterface


import android.widget.EditText





class LoginViewModel: ViewModel() {
//    private val collectionId = "608faab562521"

    private lateinit var client: Client
    private lateinit var account: Account
//    private lateinit var db: Database
//    private lateinit var storage: Storage
//    private lateinit var realtime: Realtime

//    private val _items = MutableLiveData<String>()
//    val items: LiveData<String> = _items
    fun create(context: Context) {
        client = Client(context)
            .setEndpoint("http://10.0.2.2/v1")
            .setProject("61687aa9b3b71")

        account = Account(client)
//        db = Database(client)
//        storage = Storage(client)
//        realtime = Realtime(client)
    }

    private val _user = MutableLiveData<JSONObject>().apply {
        value = null
    }
    private val _session_token = MutableLiveData<String>().apply {
        value = null
    }
    val user: LiveData<JSONObject> = _user
    val session_token: LiveData<String> = _session_token

    fun login(context: Context, mail:String,password: String) {
        // can be launched in a separate asynchronous job
        viewModelScope.launch {
            try {
                val response = account.createSession(
                    email = mail,
                    password = password
                )
                getAccount()
                var json = response.body?.string() ?: ""
                val session_token = JSONObject(json).getString("providerToken")
                _session_token.postValue(session_token)

            } catch (e: AppwriteException) {
                val invalidCredentialsMessage: String = "Invalid credentials"
                if (JSONObject(e.response).getString("message") == invalidCredentialsMessage) {
                    val username = displayNamePrompt(context)
                    createAccount(username,mail, password)
                    login(context,mail, password)
                } else {
                    Toast.makeText(
                        context,
                        JSONObject(e.response).getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }}

    private fun displayNamePrompt(context: Context) : String {
        val li = LayoutInflater.from(context)
        val promptsView: View = li.inflate(R.layout.username_box, null)

        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(
            context
        )

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView)

        val userInput = promptsView
            .findViewById(R.id.username) as EditText
        val name: String = ""
        // set dialog message
        alertDialogBuilder
            .setCancelable(false)
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, id -> // get user input and set it to result
                    // edit text
                    val name = userInput.text.toString()
                })
            .setNegativeButton("Cancel",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })


        // create alert dialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()


        // show it
        alertDialog.show()
        return name
    }
    private fun getAccount() {
        viewModelScope.launch {
            try {
                val response = account.get()
                val json = response.body?.string() ?: ""
                val user = JSONObject(json)
                _user.postValue(user)
            } catch (e: AppwriteException) {
                Log.d("Get Account", e.message.toString())
            }
        }
    }
    private fun createAccount(username: String,mail: String,password: String) {
        viewModelScope.launch {
            try {
                account.create(name=username, email=mail, password=password)
            } catch (e: AppwriteException) {
                Log.d("Get Account", e.message.toString())
            }
        }
    }
    }
