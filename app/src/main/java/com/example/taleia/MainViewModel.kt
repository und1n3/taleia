package com.example.taleia

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.appwrite.Client
import io.appwrite.exceptions.AppwriteException
import io.appwrite.services.Account
import kotlinx.coroutines.launch
import org.json.JSONObject
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import io.appwrite.services.Database

class MainViewModel: ViewModel() {
    private lateinit var client: Client
    private lateinit var account: Account
    private lateinit var database: Database
    val _session = MutableLiveData<JSONObject>().apply {
        value = null
    }

    fun create(context: Context) {
        client = Client(context)
            .setEndpoint("http://10.0.2.2/v1")
            .setProject("61687aa9b3b71")


        account = Account(client)
        database = Database(client)

    }

    fun getCurrentAccount() {
        viewModelScope.launch {
            try {
                val response = account.get()
                val json = response.body?.string()
                _session.postValue(JSONObject(json))
            }catch(e: AppwriteException) {
                Log.d("Get Account", e.message.toString())
            }}
    }
    fun saveScene(context: Context,paraula:String, escena:String,personatge:String,collectionId:String){
                viewModelScope.launch {
                    try {
                        val response = database.createDocument(
                            collectionId = collectionId,
                            data = mapOf("who" to personatge,"what" to paraula, "where" to escena),
                            read = arrayListOf("*"),
                            write = arrayListOf("*")
                        )
                        val json = response.body?.string()
                        Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show()

                    } catch (e: AppwriteException) {
                        e.printStackTrace()
                        Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }

    fun saveCharacter(context: Context,personatge:String, adjectiu:String,tick1:String,tick2:String,collectionId:String){
        viewModelScope.launch {
            try {
                val response = database.createDocument(
                    collectionId = collectionId,
                    data = mapOf("who" to personatge,"adj" to adjectiu, "tick1" to tick1,"tick2" to tick2),
                    read = arrayListOf("*"),
                    write = arrayListOf("*")
                )
                val json = response.body?.string()
                Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show()

            } catch (e: AppwriteException) {
                e.printStackTrace()
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }

    fun saveChallenge(context: Context,personatge:String, estil:String,restriccio:String,collectionId:String) {
        viewModelScope.launch {
            try {
                val response = database.createDocument(
                    collectionId = collectionId,
                    data = mapOf(
                        "what" to personatge,
                        "style" to estil,
                        "restrict" to restriccio,
                    ),
                    read = arrayListOf("*"),
                    write = arrayListOf("*")
                )
                val json = response.body?.string()
                Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show()

            } catch (e: AppwriteException) {
                e.printStackTrace()
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }


}
