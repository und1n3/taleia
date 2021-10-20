package com.example.taleia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.taleia.ui.login.LoginActivity;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openEscenaActivity(View view){

        Intent i =new Intent(this, EscenaActivity.class);
        startActivity(i);

    }

    public void openPersonatgeActivity (View view){

        Intent i =new Intent(this, PersonatgeActivity.class);
        startActivity(i);

    }

    public void openFraseActivity(View view){

        Intent i =new Intent(this, FraseActivity.class);
        startActivity(i);

    }
    public void openInfoActivity(View view){

        Intent i =new Intent(this, InfoActivity.class);
        startActivity(i);

    }
    public void openSettingsActivity(View view){

        Intent i =new Intent(this, SettingsActivity.class);
        startActivity(i);

    }


    public void openLoginActivity(View view) {

        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }
}
