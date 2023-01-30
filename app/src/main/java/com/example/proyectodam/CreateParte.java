package com.example.proyectodam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateParte extends AppCompatActivity {

    Button btnAnadirReg;
    EditText etCliente, etPoblacion, etComentarios;
    private FirebaseFirestore miFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_parte);

        setTitle("Crear Parte de Visita");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        miFirestore = FirebaseFirestore.getInstance();


        etCliente = findViewById(R.id.etCliente);
        etPoblacion = findViewById(R.id.etPoblacion);
        etComentarios = findViewById(R.id.etComentarios);
        btnAnadirReg = findViewById(R.id.btnAnadirReg);

        btnAnadirReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = etCliente.getText().toString().trim();
                String Poblacion = etPoblacion.getText().toString().trim();
                String Cometarios = etComentarios.getText().toString().trim();

                if(Nombre.isEmpty() && Poblacion.isEmpty() && Cometarios.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Rellenar todos los campos!", Toast.LENGTH_SHORT).show();
            }else{
                    insertVisita(Nombre, Poblacion, Cometarios);
                }
            }

        });
    }

    private void insertVisita(String nombre, String poblacion, String cometarios) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("población", poblacion);
        map.put("cometarios", cometarios);


        miFirestore.collection("visita").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Registro de visita realizado con éxito!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al insertar datos!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}