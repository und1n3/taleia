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

class MainViewModel: ViewModel() {
    private lateinit var client: Client
    private lateinit var account: Account
    val _session = MutableLiveData<JSONObject>().apply {
        value = null
    }

    fun create(context: Context) {
        client = Client(context)
            .setEndpoint("http://10.0.2.2/v1")
            .setProject("61687aa9b3b71")

        account = Account(client)

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
}
