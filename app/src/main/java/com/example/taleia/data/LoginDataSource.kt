package com.example.taleia.data

import com.example.taleia.data.model.LoggedInUser
import java.io.IOException

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.appwrite.Client
import io.appwrite.services.Account
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import android.util.Log

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource:ViewModel() {

    fun login(context: Context, username: String, password: String): Result<LoggedInUser> {

        try {
            // TODO: handle loggedInUser authentication
            val client = Client(context)
            .setEndpoint("http://10.0.2.2/v1") // Your API Endpoint
            .setProject("61687aa9b3b71") // Your project ID

            val account = Account(client)

            val _user = MutableLiveData<JSONObject>().apply {
                value = null
            }

            val user: LiveData<JSONObject> = _user

            viewModelScope.launch {
                val response = account.createSession(
                    email = username,
                    password = password
                )
                val json = response.body?.string() ?: ""
                val user = JSONObject(json)
                _user.postValue(user)

            }

            val LoggedUser = LoggedInUser(java.util.UUID.randomUUID().toString(), _user.toString())

            return Result.Success(LoggedUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}