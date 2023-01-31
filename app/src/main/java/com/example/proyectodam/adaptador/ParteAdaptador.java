package com.example.proyectodam.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodam.R;
import com.example.proyectodam.modelo.Parte;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ParteAdaptador extends FirestoreRecyclerAdapter<Parte, ParteAdaptador.ViewHolder>{


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ParteAdaptador(@NonNull FirestoreRecyclerOptions<Parte> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Parte Parte) {
        // Hace Biding, pinta en pantalla

        holder.fecha.setText(Parte.getFecha());
        holder.nombre.setText(Parte.getNombre());
        holder.poblacion.setText(Parte.getPoblacion());

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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fecha = itemView.findViewById(R.id.txtvFecha);
            nombre = itemView.findViewById(R.id.txtvNombre);
            poblacion = itemView.findViewById(R.id.txtvPoblacion);
        }
    }
}
