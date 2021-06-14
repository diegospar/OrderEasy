package com.example.ordereasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ordereasy.models.Platillo;
import com.google.firebase.database.DataSnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Ticket extends AppCompatActivity {
        DatosTicket ticket = new DatosTicket();
        ArrayList <InfoTicket> rellenaTicket = new ArrayList<InfoTicket>();
        String total, restauranteQR, mesaQR, ordenes,platillostotales,tPago;
        Bundle bundle;
        ConsultasSQL consultasSQL = new ConsultasSQL();
        EscrituraSQL escrituraSQL = new EscrituraSQL();
        int numTickets;
        TextView hora, fecha, mesa, mensajePago, mensaje, aviso, totalT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewTicket);

        hora = findViewById(R.id.textViewHora);
        fecha = findViewById(R.id.textViewFecha);
        mesa = findViewById(R.id.textViewMesa);
        mensajePago = findViewById(R.id.textViewMensajePago);
        mensaje = findViewById(R.id.textViewMensaje);
        aviso = findViewById(R.id.Recibo);
        totalT = findViewById(R.id.textViewTotal);

        bundle = getIntent().getExtras();
        total = String.valueOf(bundle.getString("total"));
        restauranteQR = bundle.getString("restauranteQR") ;
        mesaQR = bundle.getString("mesaQR") ;
        ordenes = bundle.getString("ordenes");
        platillostotales = bundle.getString("platillos");
        tPago = bundle.getString("tPago");

        hora.setText(hour());
        fecha.setText(date());
        mesa.setText("Mesa " + mesaQR.substring(2));
        totalT.setText("$" + total);
        if(tPago.equals("0")){
            aviso.setText("RECIBO POR PAGAR");
            mensajePago.setText("A pagar:");
            mensaje.setText("Efectúe su pago");
            escrituraSQL.efectivoMesa(restauranteQR,mesaQR);
        }

        new Thread(() -> {
            try {
             numTickets = (int) consultasSQL.getTickets(restauranteQR).getChildrenCount();
             DataSnapshot dPlatillos = consultasSQL.getPlatillos(restauranteQR);
                String[] aPlatillo = platillostotales.split(",");
                for (int i=0; i< aPlatillo.length; i++){
                   InfoTicket t = new InfoTicket();

                   t.nombre = dPlatillos.child(aPlatillo[i].substring(0,4)).
                           child(aPlatillo[i].substring(4,8)).child("nombre").getValue().toString();

                    t.precio = dPlatillos.child(aPlatillo[i].substring(0,4)).
                            child(aPlatillo[i].substring(4,8)).child("precio").getValue().toString();

                    rellenaTicket.add(t);
                }

                ticket.fecha= date();
                ticket.id = idTicket(numTickets);
                ticket.mesa = mesaQR;
                ticket.platillos = platillostotales;
                ticket.tipo_pago = Integer.parseInt(tPago);
                ticket.total = Integer.parseInt(total);
                escrituraSQL.ticket(restauranteQR,ticket.id,ticket);
                escrituraSQL.remplazaTotal(restauranteQR,mesaQR,"0");
                escrituraSQL.remplazaOrdenes(restauranteQR,mesaQR,"");
                escrituraSQL.sesion(restauranteQR,mesaQR,"nombre","");
                escrituraSQL.mesa(restauranteQR,mesaQR,"estado",1);

                runOnUiThread(() -> {
                    recyclerView.setAdapter(new TicketAdapter(rellenaTicket));
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

    }


    public String date(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateformatted = dateFormat.format(date);
        return dateformatted;

    }

    public String hour(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String dateformatted = dateFormat.format(date);
        return dateformatted;
    }

    public  String idTicket (int numTickets){
        if (numTickets<10){
            return "T00"+ String.valueOf(numTickets);
        }else if(numTickets<100){
            return "T0"+ String.valueOf(numTickets);
        }else return "T"+ String.valueOf(numTickets);

    }

    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

}