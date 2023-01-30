package com.example.proyectodam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link create_parte_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class create_parte_Fragment extends DialogFragment {

    Button btnAnadirReg;
    EditText etCliente, etPoblacion, etComentarios;
    private FirebaseFirestore miFirestore;


    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_create_parte_, container, false);
        miFirestore = FirebaseFirestore.getInstance();

        etCliente = vista.findViewById(R.id.etCliente);
        etPoblacion = vista.findViewById(R.id.etPoblacion);
        etComentarios = vista.findViewById(R.id.etComentarios);
        btnAnadirReg = vista.findViewById(R.id.btnAnadirReg);

        //click botón
        btnAnadirReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = etCliente.getText().toString().trim();
                String Poblacion = etPoblacion.getText().toString().trim();
                String Cometarios = etComentarios.getText().toString().trim();

                if(Nombre.isEmpty() || Poblacion.isEmpty() || Cometarios.isEmpty()){
                    Toast.makeText(getContext(), "Rellenar todos los campos!", Toast.LENGTH_SHORT).show();
                }else{
                    insertVisita(Nombre, Poblacion, Cometarios);
                }
            }

        });
        return vista;
    }

    private void insertVisita(String nombre, String poblacion, String cometarios) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("población", poblacion);
        map.put("cometarios", cometarios);


        miFirestore.collection("visita").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Registro de visita realizado con éxito!", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al insertar datos!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}