package com.example.ordereasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PantallaCarga extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        ImageView r1 = findViewById(R.id.Rest1);

        img= findViewById(R.id.imageView2);
        (new Handler()).postDelayed(this::openNewActivity, 3000);
    }
    public void openNewActivity(){
        Intent intent = new Intent(this, PantallaInicio.class);
        startActivity(intent);
    }
}