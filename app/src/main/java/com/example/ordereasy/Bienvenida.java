package com.example.ordereasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ordereasy.models.Platillo;

import java.util.ArrayList;

public class Bienvenida extends AppCompatActivity {

    ImageView img;
    TextView txt;
    Bundle bundle;
    ConsultasSQL consulta = new ConsultasSQL();
    ArrayList<Platillo> platillos = new ArrayList<Platillo>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        bundle= getIntent().getExtras();
        txt = findViewById(R.id.textView3);
        String restauranteQR = bundle.getString("restauranteQR");
        String mesaQR = bundle.getString("mesaQR");



        txt.setText(bundle.getString("nombre") + " ¡");

        img= findViewById(R.id.imageView7);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity(mesaQR,restauranteQR);
            }
        });
    }
    public void openNewActivity(String mesaQR, String restauranteQR){
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("mesaQR", mesaQR);
        intent.putExtra("restauranteQR",restauranteQR);
        startActivity(intent);
    }

    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}