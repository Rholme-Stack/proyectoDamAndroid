package com.example.proyectodam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyectodam.adaptador.ParteAdaptador;
import com.example.proyectodam.modelo.Parte;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    Button BtnAnadir, btnAddFrag;
    RecyclerView mainRecycler;
    ParteAdaptador mainAdaptador;
    FirebaseFirestore mainFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("App Venta CRM");

        //declarar firestore para recyclerView
        mainFirestore = FirebaseFirestore.getInstance();
        mainRecycler = findViewById(R.id.rvMain);
        mainRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mainFirestore.collection("visita");
        FirestoreRecyclerOptions<Parte> firestoreRecyclerOptions= new FirestoreRecyclerOptions.Builder<Parte>().setQuery(query, Parte.class).build();
        mainAdaptador = new ParteAdaptador(firestoreRecyclerOptions, this);
        mainAdaptador.notifyDataSetChanged();
        mainRecycler.setAdapter(mainAdaptador);



        /*BtnAnadir = findViewById(R.id.btnAnadir);
        BtnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, CreateParte.class));
            }
        });*/


        //Botón para abrir el Fragment e insertar datos
        btnAddFrag = findViewById(R.id.btnAddFrag);
        btnAddFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create_parte_Fragment frag = new create_parte_Fragment();
                frag.show(getSupportFragmentManager(), "Abrir Fragment");

            }
        });
    }


    //metodos para el recyclerview
    @Override
    protected void onStart() {
        super.onStart();
        mainAdaptador.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdaptador.stopListening();
    }
}