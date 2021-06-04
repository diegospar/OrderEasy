package com.example.ordereasy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Registro extends AppCompatActivity {

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        View mensaje = findViewById(R.id.layout_mensaje);
        Button boton = findViewById(R.id.button);
        Button boton2 = findViewById(R.id.button2);
        EditText nombre = findViewById(R.id.editTextTextPersonName);

        bundle= getIntent().getExtras();


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
                if(nombre.getText() != null && !nombre.getText().toString().equals("")  ){

                    Intent intent = new Intent(Registro.this, Bienvenida.class);
                    intent.putExtra("restauranteQR", bundle.getString("restauranteQR"));
                    intent.putExtra("mesaQR", bundle.getString("mesaQR"));
                    intent.putExtra("nombre", nombre.getText().toString());
                    startActivity(intent);


                }else{
                    Toast.makeText(Registro.this, "Introduzca un Nombre", Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}