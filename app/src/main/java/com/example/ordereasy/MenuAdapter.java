package com.example.ordereasy;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    ArrayList<Platillo> menú;

    public MenuAdapter(ArrayList<Platillo> menú){
    this.menú = menú;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new ViewHolderCategoria(LayoutInflater.from(parent.getContext()).inflate(R.layout.categorias,parent,false));
        } else {
            return new ViewHolderPlatillo(LayoutInflater.from(parent.getContext()).inflate(R.layout.platillos,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        for (Platillo mPlatillo: menú) {
            if (mPlatillo.viewType == 1) {
                ((ViewHolderCategoria) holder).setCategoria(mPlatillo);
            } else {
                ((ViewHolderPlatillo) holder).setPlatillo(mPlatillo);
            }
        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolderCategoria extends RecyclerView.ViewHolder{
        public TextView categoria;
        public ViewHolderCategoria(@NonNull View itemView) {
            super(itemView);
            categoria = itemView.findViewById(R.id.Categoria);
        }

        void setCategoria(Platillo menu){
            categoria.setText(menu.nombre);
        }
    }

    public class ViewHolderPlatillo extends RecyclerView.ViewHolder {

        public TextView nombre, precio, descripcion;
        public Button botonAgregar,botonQuitar;

        public ViewHolderPlatillo(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.Titulo);
            precio = itemView.findViewById(R.id.Precio);
            descripcion = itemView.findViewById(R.id.Descripcion);
            botonAgregar = itemView.findViewById(R.id.buttonA);
            botonQuitar = itemView.findViewById(R.id.buttonQ);
        }

        void setPlatillo(Platillo platillo){
            nombre.setText(platillo.nombre);
            precio.setText(platillo.precio);
            descripcion.setText(platillo.descripcion);
        }

    }
}
