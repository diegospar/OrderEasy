package com.example.ordereasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Registro extends AppCompatActivity {


    EscrituraSQL escrituras = new EscrituraSQL();
    ConsultasSQL consultasSQL = new ConsultasSQL();
    String nombreSesion="";

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        View mensaje = findViewById(R.id.layout_mensaje);
        Button boton = findViewById(R.id.button);
        Button boton2 = findViewById(R.id.aceptarPago);
        EditText nombre = findViewById(R.id.editTextTextPersonName);
        TextView textoMensaje = findViewById(R.id.textoMensaje);

        bundle = getIntent().getExtras();
        String estado =bundle.getString("estado");
        String mesa = bundle.getString("mesaQR");
        String restaurante = bundle.getString("restauranteQR");

        consultasSQL.getSesionName(restaurante,mesa).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nombreSesion = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if (estado.equals("1")){ //la mesa está disponible

            boton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mensaje.setVisibility(View.GONE);
                    nombre.setVisibility(View.VISIBLE);
                    boton.setVisibility(View.VISIBLE);
                }
            });


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText() != null && !nombre.getText().toString().equals("")) {

                    escrituras.sesion(restaurante,mesa,"nombre", nombre.getText().toString());
                    Intent intent = new Intent(Registro.this, Bienvenida.class);
                    intent.putExtra("restauranteQR", restaurante);
                    intent.putExtra("mesaQR", mesa);
                    intent.putExtra("nombre", nombre.getText().toString());
                    escrituras.mesa(bundle.getString("restauranteQR"),bundle.getString("mesaQR"),"estado",0);
                    startActivity(intent);


                } else {
                    Toast.makeText(Registro.this, "Introduzca un Nombre", Toast.LENGTH_LONG).show();
                }


            }
        });
    }else {
            textoMensaje.setText("Sesion iniciada previamente. Ingrese el nombre usado como contraseña.");
            boton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mensaje.setVisibility(View.GONE);
                    nombre.setVisibility(View.VISIBLE);
                    boton.setVisibility(View.VISIBLE);
                }
            });


            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nombre.getText() != null && !nombre.getText().toString().equals("")) {
                        consultasSQL.getSesionName(restaurante,mesa).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                            nombreSesion = snapshot.getValue().toString();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        if (nombreSesion.equals(nombre.getText().toString())){
                            Intent intent = new Intent(Registro.this, Bienvenida.class);
                            intent.putExtra("restauranteQR", bundle.getString("restauranteQR"));
                            intent.putExtra("mesaQR", bundle.getString("mesaQR"));
                            intent.putExtra("nombre", nombre.getText().toString());
                            startActivity(intent);
                        }else Toast.makeText(Registro.this, "Nombre incorrecto", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(Registro.this, "Introduzca un Nombre", Toast.LENGTH_LONG).show();
                    }


                }
            });

        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_registro);
        View mensaje = findViewById(R.id.layout_mensaje);
        Button boton = findViewById(R.id.button);
        Button boton2 = findViewById(R.id.aceptarPago);
        EditText nombre = findViewById(R.id.editTextTextPersonName);
        TextView textoMensaje = findViewById(R.id.textoMensaje);

        bundle = getIntent().getExtras();
        String estado =bundle.getString("estado");

        if (estado.equals("1")){ //la mesa está disponible

            boton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mensaje.setVisibility(View.GONE);
                    nombre.setVisibility(View.VISIBLE);
                    boton.setVisibility(View.VISIBLE);
                }
            });


            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nombre.getText() != null && !nombre.getText().toString().equals("")) {

                        Intent intent = new Intent(Registro.this, Bienvenida.class);
                        intent.putExtra("restauranteQR", bundle.getString("restauranteQR"));
                        intent.putExtra("mesaQR", bundle.getString("mesaQR"));
                        intent.putExtra("nombre", nombre.getText().toString());
                        escrituras.mesa(bundle.getString("restauranteQR"),bundle.getString("mesaQR"),"estado",0);
                        startActivity(intent);


                    } else {
                        Toast.makeText(Registro.this, "Introduzca un Nombre", Toast.LENGTH_LONG).show();
                    }


                }
            });
        }else {
            textoMensaje.setText("Sesion iniciada previamente. Ingrese el nombre usado como contraseña.");
            boton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mensaje.setVisibility(View.GONE);
                    nombre.setVisibility(View.VISIBLE);
                    boton.setVisibility(View.VISIBLE);
                }
            });


            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (nombre.getText() != null && !nombre.getText().toString().equals("")) {

                        Intent intent = new Intent(Registro.this, Bienvenida.class);
                        intent.putExtra("restauranteQR", bundle.getString("restauranteQR"));
                        intent.putExtra("mesaQR", bundle.getString("mesaQR"));
                        intent.putExtra("nombre", nombre.getText().toString());
                        startActivity(intent);


                    } else {
                        Toast.makeText(Registro.this, "Introduzca un Nombre", Toast.LENGTH_LONG).show();
                    }


                }
            });

        }
    }
}