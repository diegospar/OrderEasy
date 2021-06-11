package com.example.ordereasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.widget.TextView;

public class Sesion extends AppCompatActivity {

    CardView menu;
    Bundle bundle;
    TextView nombreR, numeroM;
    ConsultasSQL consultasSQL = new ConsultasSQL();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion);
        bundle= getIntent().getExtras();
        nombreR = findViewById(R.id.textRestaurante);
        numeroM = findViewById(R.id.textMesa);
        String restauranteQR = bundle.getString("restauranteQR");
        String mesaQR = bundle.getString("mesaQR");
        //String nombreRestaurante =  consultasSQL.restaurante(restauranteQR,"Nombre");
        //nombreR.setText(nombreRestaurante);

    }
}