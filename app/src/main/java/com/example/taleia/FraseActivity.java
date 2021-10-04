package com.example.taleia;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class FraseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frase);
        final Button prompt = findViewById(R.id.paraula_boto); // buscar botons del layout
        final Button estil = findViewById(R.id.estil_boto);// hi posem final pq hi accedim a dins d classes
        final Button restriccio = findViewById(R.id.restriccio_boto);

        Resources res = getResources(); //agafa llista del fitxer
        final String[] prompts = res.getStringArray(R.array.paraules); //
        final String[] style = res.getStringArray(R.array.estils); //
        final String[] restr= res.getStringArray(R.array.restricc);



        prompt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(574); //sera de 0 a 9

                prompt.setText(prompts[n]);
            }
        });


        estil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(23); //sera de 0 a 9

                estil.setText(style[n]);
            }
        });


        restriccio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random rand = new Random();
                int n = rand.nextInt(76);

                restriccio.setText(restr[n]);
            }
        });
    }
}
