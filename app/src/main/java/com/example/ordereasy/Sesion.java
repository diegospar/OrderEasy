package com.example.ordereasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

public class Sesion extends AppCompatActivity {

    CardView menu, atencion;
    Bundle bundle;
    RadioGroup metodoDP;
    ImageView cerrarMensaje;
    TextView nombreR, numeroM, txtVM1, vMPlatillos1, vMTotal1, txtVM2, vMPlatillos2, vMTotal2, txtVM3,
            vMPlatillos3, vMTotal3;
    View orden1, orden2, orden3, vM1, vM2, vM3, principal, mensaje;
    EscrituraSQL escrituraSQL = new EscrituraSQL();
    ConsultasSQL consultasSQL = new ConsultasSQL();
    Button cancelar1, cancelar2, cancelar3, preparacion1, preparacion2, preparacion3, completado1,
    completado2, completado3, pagar, aceptarPago;
    String platillos1="", nombrePlatillos1,platillos2="", nombrePlatillos2, platillos3="", nombrePlatillos3,
            total, total1, total2, total3, platillostotales;
    int caso1=0, caso2=0, caso3=0;

    public int estado1, estado2, estado3, estado4;
    public String ordenes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        bundle= getIntent().getExtras();
        nombreR = findViewById(R.id.textNombreRestaurante);
        numeroM = findViewById(R.id.textNumMesa);
        menu = findViewById(R.id.cardViewMenu);

        cancelar1 = findViewById(R.id.buttonCancelar);
        preparacion1=findViewById(R.id.buttonEnPreparacion);
        completado1 = findViewById(R.id.buttonCompletado);

        cancelar2 = findViewById(R.id.buttonCancelar2);
        preparacion2=findViewById(R.id.buttonEnPreparacion2);
        completado2 = findViewById(R.id.buttonCompletado2);

        cancelar3 = findViewById(R.id.buttonCancelar3);
        preparacion3=findViewById(R.id.buttonEnPreparacion3);
        completado3 = findViewById(R.id.buttonCompletado3);

        orden1 = findViewById(R.id.Orden1);
        orden2 = findViewById(R.id.Orden2);
        orden3 = findViewById(R.id.Orden3);
        pagar = findViewById(R.id.buttonPagar);

        txtVM1 = findViewById(R.id.textVM1);
        vM1 = findViewById(R.id.vM1);
        vMPlatillos1 = findViewById(R.id.vMPlatillos1);
        vMTotal1 = findViewById(R.id.vMTotal1);

        txtVM2 = findViewById(R.id.textVM2);
        vM2 = findViewById(R.id.vM2);
        vMPlatillos2 = findViewById(R.id.vMPlatillos2);
        vMTotal2 = findViewById(R.id.vMTotal2);

        txtVM3 = findViewById(R.id.textVM3);
        vM3 = findViewById(R.id.vM3);
        vMPlatillos3 = findViewById(R.id.vMPlatillos3);
        vMTotal3 = findViewById(R.id.vMTotal3);

        principal= findViewById(R.id.principal);
        mensaje = findViewById(R.id.layout_mensajeSalida);
        cerrarMensaje = findViewById(R.id.cerrarMensaje);
        aceptarPago= findViewById(R.id.buttonSalir);
        metodoDP = findViewById(R.id.radioGroup);




        String restauranteQR = bundle.getString("restauranteQR");
        String mesaQR = bundle.getString("mesaQR");
        nombreR.setText(RestaurantesInicio.nombreRestaurante(restauranteQR));
        numeroM.setText("Mesa " + mesaQR.substring(2) );
        atencion = findViewById(R.id.atencion);

        atencion.setOnClickListener(v -> {
            escrituraSQL.atencionMesa(restauranteQR, mesaQR);
            Toast.makeText(Sesion.this, "MESERO NOTIFICADO", Toast.LENGTH_LONG).show();
        });

        txtVM1.setOnClickListener(v -> {
            if (txtVM1.getText().toString().equals("ver más")){
                txtVM1.setText("ver menos");
                vM1.setVisibility(View.VISIBLE);
            }else{
                vM1.setVisibility(View.GONE);
                txtVM1.setText("ver más");
            }
        });

        txtVM2.setOnClickListener(v -> {
            if (txtVM2.getText().toString().equals("ver más")){
                txtVM2.setText("ver menos");
                vM2.setVisibility(View.VISIBLE);
            }else{
                vM2.setVisibility(View.GONE);
                txtVM2.setText("ver más");
            }
        });

        txtVM3.setOnClickListener(v -> {
            if (txtVM3.getText().toString().equals("ver más")){
                txtVM3.setText("ver menos");
                vM3.setVisibility(View.VISIBLE);
            }else{
                vM3.setVisibility(View.GONE);
                txtVM3.setText("ver más");
            }
        });

        menu.setOnClickListener(v -> {
            Intent intent = new Intent(Sesion.this, Menu.class);
            intent.putExtra("mesaQR", mesaQR);
            intent.putExtra("restauranteQR",restauranteQR);
            startActivity(intent);
        });

        new Thread(() -> {

            try {

                DataSnapshot dsSesion= consultasSQL.getSesion(restauranteQR,mesaQR);
                ordenes =dsSesion.child("ordenes").getValue().toString();
                total = dsSesion.child("total").getValue().toString();

                if (!ordenes.equals("")) {
                    if(ordenes.length()>=4){
                        DataSnapshot dOrden1 = consultasSQL.getOrderState(restauranteQR,ordenes.substring(0,4));
                        estado1 = Integer.parseInt(dOrden1.child("estado").getValue().toString());
                        total1 = dOrden1.child("total").getValue().toString();
                        vMTotal1.setText(total1);
                        platillos1 = dOrden1.child("platillos").getValue().toString();
                        String[] aPlatillo1 = platillos1.split(",");
                        runOnUiThread(() -> {
                            orden1.setVisibility(View.VISIBLE);
                            orden2.setVisibility(View.GONE);
                            orden3.setVisibility(View.GONE);
                            pagar.setVisibility(View.GONE);
                        });
                        if(estado1==0){
                            cancelar1.setVisibility(View.VISIBLE);
                            preparacion1.setVisibility(View.GONE);
                            completado1.setVisibility(View.GONE);
                        }else if (estado1==1){
                            cancelar1.setVisibility(View.GONE);
                            preparacion1.setVisibility(View.VISIBLE);
                            completado1.setVisibility(View.GONE);
                        }else {
                            cancelar1.setVisibility(View.GONE);
                            preparacion1.setVisibility(View.GONE);
                            completado1.setVisibility(View.VISIBLE);
                            if(ordenes.length()<=4 ){
                                runOnUiThread(() -> {
                                pagar.setVisibility(View.VISIBLE);
                                });
                            }
                        }
                            for (int i=0; i< aPlatillo1.length; i++){
                                if (i==0){
                                    nombrePlatillos1 = consultasSQL.getPlatilloName(
                                            restauranteQR, aPlatillo1[i].substring(0,4),
                                            aPlatillo1[i].substring(4,8)
                                    ).getValue().toString();
                                }else
                                nombrePlatillos1 = nombrePlatillos1 + ", " + consultasSQL.getPlatilloName(
                                        restauranteQR, aPlatillo1[i].substring(0,4),
                                        aPlatillo1[i].substring(4,8)
                                ).getValue().toString();
                            }

                         vMPlatillos1.setText(nombrePlatillos1);


                    }if (ordenes.length()>=8){
                        DataSnapshot dOrden = consultasSQL.getOrderState(restauranteQR,ordenes.substring(4,8));
                        estado2 = Integer.parseInt(dOrden.child("estado").getValue().toString());
                        platillos2 = dOrden.child("platillos").getValue().toString();
                        total2 = dOrden.child("total").getValue().toString();
                        String[] aPlatillo2 = platillos2.split(",");
                        runOnUiThread(() -> {
                            orden1.setVisibility(View.VISIBLE);
                            orden2.setVisibility(View.VISIBLE);
                            orden3.setVisibility(View.GONE);
                            pagar.setVisibility(View.GONE);
                            vMTotal2.setText(total2);
                        });

                        if(estado2==0){
                            cancelar2.setVisibility(View.VISIBLE);
                            preparacion2.setVisibility(View.GONE);
                            completado2.setVisibility(View.GONE);
                        }else if (estado2==1){
                            cancelar2.setVisibility(View.GONE);
                            preparacion2.setVisibility(View.VISIBLE);
                            completado2.setVisibility(View.GONE);
                        }else if (estado2==2){
                            cancelar2.setVisibility(View.GONE);
                            preparacion2.setVisibility(View.GONE);
                            completado2.setVisibility(View.VISIBLE);
                        }
                       if(estado1==2 && estado2 == 2 && ordenes.length()<=8){
                           runOnUiThread(() -> {
                               pagar.setVisibility(View.VISIBLE);
                           });
                        }

                        for (int i=0; i< aPlatillo2.length; i++){
                            if (i==0){
                                nombrePlatillos2 = consultasSQL.getPlatilloName(
                                        restauranteQR, aPlatillo2[i].substring(0,4),
                                        aPlatillo2[i].substring(4,8)
                                ).getValue().toString();
                            }else
                                nombrePlatillos2 = nombrePlatillos2 + ", " + consultasSQL.getPlatilloName(
                                        restauranteQR, aPlatillo2[i].substring(0,4),
                                        aPlatillo2[i].substring(4,8)
                                ).getValue().toString();
                        }

                        vMPlatillos2.setText(nombrePlatillos2);

                    }if (ordenes.length()==12){
                        DataSnapshot dOrden = consultasSQL.getOrderState(restauranteQR,ordenes.substring(8,12));
                        estado3 = Integer.parseInt(dOrden.child("estado").getValue().toString());
                        platillos3 = dOrden.child("platillos").getValue().toString();
                        total3 = dOrden.child("total").getValue().toString();

                        String[] aPlatillo3 = platillos3.split(",");
                        runOnUiThread(() -> {
                            orden1.setVisibility(View.VISIBLE);
                            orden2.setVisibility(View.VISIBLE);
                            orden3.setVisibility(View.VISIBLE);
                            pagar.setVisibility(View.GONE);
                            vMTotal3.setText(total3);
                        });
                        if(estado3==0){
                                cancelar3.setVisibility(View.VISIBLE);
                                preparacion3.setVisibility(View.GONE);
                                completado3.setVisibility(View.GONE);

                        }else if (estado3==1){
                                cancelar3.setVisibility(View.GONE);
                                preparacion3.setVisibility(View.VISIBLE);
                                completado3.setVisibility(View.GONE);

                        }else {
                                cancelar3.setVisibility(View.GONE);
                                preparacion3.setVisibility(View.GONE);
                                completado3.setVisibility(View.VISIBLE);
                        }
                        if(estado1==2 && estado2 == 2 && estado3 == 2 && ordenes.length()<=12){
                            runOnUiThread(() -> {
                                pagar.setVisibility(View.VISIBLE);
                            });
                        }

                        for (int i=0; i< aPlatillo3.length; i++){
                            if (i==0){
                                nombrePlatillos3 = consultasSQL.getPlatilloName(
                                        restauranteQR, aPlatillo3[i].substring(0,4),
                                        aPlatillo3[i].substring(4,8)
                                ).getValue().toString();
                            }else
                                nombrePlatillos3 = nombrePlatillos2 + ", " + consultasSQL.getPlatilloName(
                                        restauranteQR, aPlatillo3[i].substring(0,4),
                                        aPlatillo3[i].substring(4,8)
                                ).getValue().toString();
                        }

                        vMPlatillos3.setText(nombrePlatillos3);

                    }
                }else {
                    orden1.setVisibility(View.GONE);
                    orden2.setVisibility(View.GONE);
                    orden3.setVisibility(View.GONE);
                }
                runOnUiThread(() -> {

                });


            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();


        cancelar1.setOnClickListener(v -> {
            orden1.setVisibility(View.GONE);
            Toast.makeText(Sesion.this, "Orden Cancelada", Toast.LENGTH_LONG).show();
            escrituraSQL.eliminaOrden(restauranteQR, ordenes.substring(0, 4));
            ordenes = ordenes.replace(ordenes.substring(0,4),"");
            total = String.valueOf(Integer.parseInt(total) - Integer.parseInt(total1));
            escrituraSQL.remplazaTotal(restauranteQR, mesaQR, total);
            escrituraSQL.remplazaOrdenes(restauranteQR, mesaQR,ordenes);
        });

        cancelar2.setOnClickListener(v -> {
            orden2.setVisibility(View.GONE);
            Toast.makeText(Sesion.this, "Orden Cancelada", Toast.LENGTH_LONG).show();
            escrituraSQL.eliminaOrden(restauranteQR, ordenes.substring(4, 8));
            ordenes = ordenes.replace(ordenes.substring(4,8),"");
            total = String.valueOf(Integer.parseInt(total) - Integer.parseInt(total2));
            escrituraSQL.remplazaTotal(restauranteQR, mesaQR, total);
            escrituraSQL.remplazaOrdenes(restauranteQR, mesaQR,ordenes);
        });

        cancelar3.setOnClickListener(v -> {
            orden3.setVisibility(View.GONE);
            Toast.makeText(Sesion.this, "Orden Cancelada", Toast.LENGTH_LONG).show();
            escrituraSQL.eliminaOrden(restauranteQR, ordenes.substring(8, 12));
            ordenes = ordenes.replace(ordenes.substring(8,12),"");
            total = String.valueOf(Integer.parseInt(total) - Integer.parseInt(total3));
            escrituraSQL.remplazaTotal(restauranteQR, mesaQR, total);
            escrituraSQL.remplazaOrdenes(restauranteQR, mesaQR,ordenes);

        });

        pagar.setOnClickListener(v -> {
            principal.setVisibility(View.GONE);
            mensaje.setVisibility(View.VISIBLE);
        });

        cerrarMensaje.setOnClickListener(v -> {
            principal.setVisibility(View.VISIBLE);
            mensaje.setVisibility(View.GONE);
            Toast.makeText(Sesion.this, "Pago cancelado", Toast.LENGTH_LONG).show();
        });

        aceptarPago.setOnClickListener(v -> {
            if(platillos3.equals("")){
                platillostotales = platillos1 +"," +platillos2;
            }else if(platillos2.equals("")){
                platillostotales = platillos1;
            }else {platillostotales = platillos1 +"," +platillos2+ "," + platillos3;}
          if (metodoDP.getCheckedRadioButtonId()==R.id.radioButtonEfectivo){
              Intent intent = new Intent(Sesion.this, Ticket.class);
              intent.putExtra("mesaQR", mesaQR);
              intent.putExtra("restauranteQR", restauranteQR);
              intent.putExtra("total", total);
              intent.putExtra("ordenes", ordenes);
              intent.putExtra("platillos", platillostotales);
              intent.putExtra("tPago","0");
              startActivity(intent);

            }else if (metodoDP.getCheckedRadioButtonId()==R.id.radioButtonTarjeta) {
              Intent intent = new Intent(Sesion.this, CardActivity.class);
              intent.putExtra("mesaQR", mesaQR);
              intent.putExtra("restauranteQR", restauranteQR);
              intent.putExtra("total", total);
              intent.putExtra("ordenes", ordenes);
              intent.putExtra("platillos", platillostotales);
              intent.putExtra("tPago","1");
              startActivity(intent);
          }

        });



    }

    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}