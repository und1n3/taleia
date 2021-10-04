package com.example.taleia;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class EscenaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escena);

        final Button paraula = findViewById(R.id.paraula_boto); // buscar botons del layout
        final Button escenari = findViewById(R.id.escenari_boto);// hi posem final pq hi accedim a dins d classes
        final Button personatge = findViewById(R.id.personatge_boto);

        Resources res = getResources(); //agafa llista del fitxer
        final String[] mots = res.getStringArray(R.array.paraules);
        final String[] escena = res.getStringArray(R.array.escenes);
        final String[] protagonista = res.getStringArray(R.array.personatges);


        paraula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(574);

                paraula.setText(mots[n]);
            }
        });


        escenari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(108); //sera de 0 a 9

                escenari.setText(escena[n]);
            }
        });

        personatge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(150); //sera de 0 a 9

                personatge.setText(protagonista[n]);
            }
        });


    }
}
