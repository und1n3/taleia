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
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import io.appwrite.services.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import okhttp3.internal.wait
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel: ViewModel() {
    private lateinit var client: Client
    private lateinit var account: Account
    private lateinit var database: Database
    val _session = MutableLiveData<JSONObject>().apply {
        value = null
    }
    private val _documentList = MutableLiveData<ArrayList<String?>>()
    val documentlist: LiveData<ArrayList<String?>> = _documentList


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
                            data = mapOf("who" to personatge,"what" to paraula, "where" to escena)
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
                    data = mapOf("who" to personatge,"adj" to adjectiu, "tick1" to tick1,"tick2" to tick2)
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
                    )
                )
                val json = response.body?.string()
                Toast.makeText(context, "Saved!", Toast.LENGTH_LONG).show()

            } catch (e: AppwriteException) {
                e.printStackTrace()
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
    fun listDocuments(context: Context, collectionId: String, offset: Int) : ArrayList<String>{
        val docs : ArrayList<String> = ArrayList()

        viewModelScope.launch {
            val session = account.get()

            val json = session.body?.string() ?: ""
            val sessionJSON :JSONObject = JSONObject(json)
            val id: String = sessionJSON.getString("$"+"id")
            val response = database.listDocuments(
                collectionId = collectionId,
                offset = offset,
            )
            val a =JSONObject(response.body?.string())
            val documents = a.getJSONArray("documents")

            for (i in 0 until documents.length()) {
                val item = documents.getJSONObject(i)
                if (id in item.getJSONObject("$"+"permissions").getString("read")){
                    val card = "WHO: "+item.getString("who")+
                            " / WHAT: "+ item.getString("what")+
                            " / WHERE: "+item.getString("where")
                    docs.add(card)
                    _documentList.value?.add(card)
                }
            }


            Toast.makeText(context,docs.toString(),Toast.LENGTH_LONG).show()

//            _documentList.postValue(docs.toSet())
        }

        return docs

    }
}
