package com.example.taleia.data;

import com.example.taleia.data.model.LoggedInUser;

import java.io.IOException;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.launch;

import io.appwrite.Client;
import io.appwrite.services.Account;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource extends AppCompatActivity {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            Client client = new Client(getApplicationContext())
                    .setEndpoint("https://[HOSTNAME_OR_IP]/v1") // Your API Endpoint
                    .setProject("5df5acd0d48c2"); // Your project ID

            Account account = new Account(client);

            account.createSession(
                    "email@example.com",
                    "password",
            new Continuation<Object>() {
                @NotNull
                @Override
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }

                @Override
                public void resumeWith(@NotNull Object o) {
                    String json = "";
                    try {
                        if (o instanceof Result.Failure) {
                            Result.Failure failure = (Result.Failure) o;
                            throw failure.exception;
                        } else {
                            Response response = (Response) o;
                            json = response.body().string();
                        }
                    } catch (Throwable th) {
                        Log.e("ERROR", th.toString());
                    }
                }
            }
        );

            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
