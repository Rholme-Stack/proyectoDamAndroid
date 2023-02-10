package com.example.proyectodam;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
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

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
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
       //instacia firebase
        miFirestore = FirebaseFirestore.getInstance();
        //Recoge las variables
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
                //Date Fecha = new Date(); CharSequence s = DateFormat.format("dd/MM/yyyy ", Fecha.getTime());
                //Se recoge la fecha actual aqui en este variable y se pone formato
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String Fecha = sdf.format(new Date());


                if(Nombre.isEmpty() || Poblacion.isEmpty() || Cometarios.isEmpty()){

                    Toast.makeText(requireContext(), "Rellenar todos los campos!", Toast.LENGTH_SHORT).show();


                }else{

                    insertVisita(Nombre, Poblacion, Cometarios, Fecha);
                }
            }

        });
        return vista;
    }

    private void insertVisita(String nombre, String poblacion, String cometarios, String Fecha) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre);
        map.put("poblacion", poblacion);
        map.put("comentarios", cometarios);
        map.put("fecha", Fecha);


        miFirestore.collection("visita").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getContext(), "Registro de visita realizado con éxito!", Toast.LENGTH_SHORT).show();
                getDialog().dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Error al insertar datos!", Toast.LENGTH_LONG).show();
            }
        });
    }
}