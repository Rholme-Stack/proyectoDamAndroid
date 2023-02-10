package com.example.proyectodam.adaptador;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam.R;
import com.example.proyectodam.modelo.Parte;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ParteAdaptador extends FirestoreRecyclerAdapter<Parte, ParteAdaptador.ViewHolder>{


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    public ParteAdaptador(@NonNull FirestoreRecyclerOptions<Parte> options, Activity activity) {
        super(options);
        this.activity = activity;
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Parte Parte) {
        //obtiene la posición del elemento
        DocumentSnapshot doc = getSnapshots().getSnapshot(holder.getBindingAdapterPosition());
        final String id = doc.getId();
        // Hace Biding, pinta en pantalla
        holder.fecha.setText(Parte.getFecha());
        holder.nombre.setText(Parte.getNombre());
        holder.poblacion.setText(Parte.getPoblacion());

        //Evento del botón eliminar para eliminar
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteVisita(id);
            }
        });

    }

    private void deleteVisita(String id) {

            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Confirmación de eliminación");
            builder.setMessage("¿Estás seguro de que deseas eliminar este registro?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mFirestore.collection("visita").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(activity, "Se ha eliminado el registro!", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(activity, "Error al eliminar este elemento!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //función para crear la vista en el parent
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_parte_solo, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //vincula los elementos con la vista
        TextView fecha, nombre, poblacion;
        ImageButton imgDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.txtvFecha);
            nombre = itemView.findViewById(R.id.txtvNombre);
            poblacion = itemView.findViewById(R.id.txtvPoblacion);
            imgDel = itemView.findViewById(R.id.imgDel);
        }
    }


}
