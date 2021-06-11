package com.example.ordereasy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ordereasy.models.Platillo;

import java.util.ArrayList;

public class CarritoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Carrito> carrito;

    public CarritoAdapter( ArrayList<Carrito> carro){
        this.carrito = carro;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CarritoAdapter.ViewHolderCarrito(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.platillo_carrito,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((CarritoAdapter.ViewHolderCarrito) holder).setPlatillo(carrito.get(position));
    }

    @Override
    public int getItemCount() {
        return carrito.size();
    }

    static class ViewHolderCarrito extends RecyclerView.ViewHolder {

        public TextView nombre, precio;
        public EditText cantidad;

        public ViewHolderCarrito(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombrePlatillo);
            precio = itemView.findViewById(R.id.precio);
            cantidad = itemView.findViewById(R.id.cantidad);

        }

        void setPlatillo(Carrito carrito){
            nombre.setText(carrito.nombre);
            precio.setText("$" + carrito.precio);
            // cantidad.getText();


        }

    }

}
