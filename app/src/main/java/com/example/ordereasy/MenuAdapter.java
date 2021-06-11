package com.example.ordereasy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordereasy.models.Platillo;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  ArrayList<Platillo> menús;
    public String idcategoria = "";

    public MenuAdapter( ArrayList<Platillo> menú){
        this.menús = menú;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 2) {
            return new ViewHolderPlatillo(LayoutInflater.from(parent.getContext()).inflate(R.layout.platillos,parent,false));
        } else{
            return new ViewHolderCategoria(LayoutInflater.from(parent.getContext()).inflate(R.layout.categorias,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (getItemViewType(position) == 2) {
                ((ViewHolderPlatillo) holder).setPlatillo(menús.get(position));
                holder.setIsRecyclable(false);
            } else
                ((ViewHolderCategoria) holder).setCategoria(menús.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return menús.size();
    }
    @Override
    public int getItemViewType (int position){
        return menús.get(position).viewType;
    }


    class ViewHolderCategoria extends RecyclerView.ViewHolder{
        public TextView categoria;
        public ViewHolderCategoria(@NonNull View itemView) {
            super(itemView);
            categoria = itemView.findViewById(R.id.Categoria);

        }

        void setCategoria(Platillo menu){
            categoria.setText(menu.nombre);
            idcategoria = menu.idCategoria;
        }
    }

    class ViewHolderPlatillo extends RecyclerView.ViewHolder {

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
            String idcat = idcategoria;
            nombre.setText(platillo.nombre);
            precio.setText("$" + platillo.precio);
            descripcion.setText(platillo.descripcion);
            botonAgregar.setOnClickListener(v -> {
                Menu.orden.agregarPlatillo(platillo.nombre,platillo.precio,platillo.id, idcat);
                botonAgregar.setVisibility(View.GONE);
                botonQuitar.setVisibility(View.VISIBLE);
            });

            botonQuitar.setOnClickListener(v -> {
                botonQuitar.setVisibility(View.GONE);
                botonAgregar.setVisibility(View.VISIBLE);
            });
        }

    }
}
