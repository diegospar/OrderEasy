package com.example.ordereasy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Registro extends AppCompatActivity {


    EscrituraSQL escrituras = new EscrituraSQL();

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        View mensaje = findViewById(R.id.layout_mensaje);
        Button boton = findViewById(R.id.button);
        Button boton2 = findViewById(R.id.button2);
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

    @Override
    protected void onRestart() {
        super.onRestart();
        setContentView(R.layout.activity_registro);
        View mensaje = findViewById(R.id.layout_mensaje);
        Button boton = findViewById(R.id.button);
        Button boton2 = findViewById(R.id.button2);
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