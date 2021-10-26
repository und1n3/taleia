package com.example.taleia.data

import com.example.taleia.data.model.LoggedInUser
import java.io.IOException

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.appwrite.Client
import io.appwrite.services.Account
import android.content.Context

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    fun login(context: Context, username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            val client = Client(context)
            .setEndpoint("http://localhost/v1") // Your API Endpoint
            .setProject("PROJECT_ID") // Your project ID

            val account = Account(client)
            val json: String = ""
            GlobalScope.launch {
                val response = account.createSession(
                    email = username,
                    password = password
                )
                val json = response.body?.string()
            }
            val LoggedUser = LoggedInUser(java.util.UUID.randomUUID().toString(), json)
            return Result.Success(LoggedUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}