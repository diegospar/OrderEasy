package com.example.ordereasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.example.ordereasy.models.Platillo;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

import static com.example.ordereasy.RestaurantesInicio.initR001;

public class Menu extends AppCompatActivity {

    Bundle bundle;
    ConsultasSQL consultasSQL = new ConsultasSQL();
    CardView cardView;
    TextView errorMessage;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        CargarSlider();
        RecyclerView  recyclerView = findViewById(R.id.recyclerView);
        bundle= getIntent().getExtras();
        cardView = findViewById(R.id.CardViewsesion);
        errorMessage = findViewById(R.id.errorMessage);
        progressBar = findViewById(R.id.progressBar);
        String idRestaurante = "R001";
        ArrayList<Platillo> menu = new ArrayList<>();
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, Sesion.class);
            startActivity(intent);
        });

        new Thread(() -> {
            try {
                progressBar.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                DataSnapshot data = consultasSQL.getFood(idRestaurante);
                for (DataSnapshot category: data.getChildren()) {
                    DataSnapshot categoryData = consultasSQL.categoría(idRestaurante, category.getKey(),"nombre_cat") ;
                    Platillo mCategoria = new Platillo();
                    mCategoria.nombre = categoryData.getValue().toString();
                    mCategoria.viewType = 1;
                    menu.add(mCategoria);

                    for (DataSnapshot platillo: category.getChildren()) {
                        Platillo tempFood = platillo.getValue(Platillo.class);
                        tempFood.viewType = 2;
                        menu.add(tempFood);
                    }
                }
                runOnUiThread(() -> {
                    recyclerView.setAdapter(new MenuAdapter(menu));
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                });


            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    errorMessage.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                });
            }

        }).start();
    }

    void CargarSlider() {
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(initR001());
    }


}