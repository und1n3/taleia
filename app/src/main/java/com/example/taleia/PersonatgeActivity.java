package com.example.taleia;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class PersonatgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personatge);

        final Button ocupacio = findViewById(R.id.ocupacio_boto); // buscar botons del layout
        final Button adjectiu = findViewById(R.id.adjectius_boto);// hi posem final pq hi accedim a dins d classes
        final Button accions = findViewById(R.id.accions_boto);
        final Button mania = findViewById(R.id.manies_boto);

        Resources res = getResources(); //agafa llista del fitxer
        final String[] ocupacions = res.getStringArray(R.array.personatges);
        final String[] adj = res.getStringArray(R.array.adjectius);
        final String[] acc = res.getStringArray(R.array.acció);
        final String[] man = res.getStringArray(R.array.paraules);



        ocupacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(150); //sera de 0 a 9

                ocupacio.setText(ocupacions[n]);
            }
        });


        adjectiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(421); //sera de 0 a 9

                adjectiu.setText(adj[n]);
            }
        });

        accions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(427); //sera de 0 a 9

                accions.setText(acc[n]);
            }
        });

        mania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí el codi pel boto
                Random rand = new Random(); //agafem num aleatori
                int n = rand.nextInt(574); //sera de 0 a 9

                mania.setText(man[n]);
            }
        });



    }
}
