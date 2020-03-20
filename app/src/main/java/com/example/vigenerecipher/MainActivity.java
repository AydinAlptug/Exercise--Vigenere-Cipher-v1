package com.example.vigenerecipher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<cipher> list;
    ListView listView;
    Button btnEncrypt, btnDecrypt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineButtons();
        giveResponsibility();
    }

    public void defineButtons() {
        btnEncrypt = findViewById(R.id.encrypt);
        btnDecrypt = findViewById(R.id.decrypt);
    }

    public void giveResponsibility() {
        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), EncryptActivity2.class);
                startActivity(intent1);
            }
        });

        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), DecryptActivity3.class);
                startActivity(intent2);
            }
        });
    }
}
