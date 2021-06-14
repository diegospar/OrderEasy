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

public class TicketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<InfoTicket> ticket; //carrito

    public TicketAdapter( ArrayList<InfoTicket> iTicket){ //carro

        this.ticket = iTicket;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolderTicket(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.platillo_ticket,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderTicket) holder).setPlatillo(ticket.get(position));
    }

    @Override
    public int getItemCount() {
        return ticket.size();
    }

    static class ViewHolderTicket extends RecyclerView.ViewHolder {

        public TextView nombre, precio;

        public ViewHolderTicket(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombrePlatillot);
            precio = itemView.findViewById(R.id.preciot);

        }

        void setPlatillo(InfoTicket t){
            nombre.setText(t.nombre);
            precio.setText("$" + t.precio);
        }

    }

}