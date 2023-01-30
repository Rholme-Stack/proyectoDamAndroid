package com.example.proyectodam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BtnAnadir, btnAddFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("App Venta CRM");

        BtnAnadir = findViewById(R.id.btnAnadir);
        BtnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, CreateParte.class));
            }
        });

        btnAddFrag = findViewById(R.id.btnAddFrag);
        btnAddFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create_parte_Fragment frag = new create_parte_Fragment();
                frag.show(getSupportFragmentManager(), "Abrir Fragment");

            }
        });
    }
}