package com.example.gkm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // xml'deki butonu tanimliyoruz.
    Button basla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // id atıyoruz, butonu kullanabilmek icin.
        basla = (Button) findViewById(R.id.basla);
        // butona tiklama ozelligi veriyoruz.
        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sayfa gecisinde Intent sinifindan yararlaniriz.
                // Intent'in icinde önce hangi activity'deyiz onu,
                // sonra gidecegimiz activity'i yazıyoruz.
                Intent gecis = new Intent(MainActivity.this, Sorular.class);
                startActivity(gecis);
            }
        });
    }
}
