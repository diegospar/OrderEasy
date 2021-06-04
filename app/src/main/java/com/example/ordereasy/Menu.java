package com.example.ordereasy;

import android.content.Intent;
import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.example.ordereasy.RestaurantesInicio.*;

public class Menu extends AppCompatActivity {

    Bundle bundle;
    ConsultasSQL consultasSQL = new ConsultasSQL();
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        CargarSlider();
        RecyclerView  recyclerView = findViewById(R.id.recyclerView);
        bundle= getIntent().getExtras();
        cardView = findViewById(R.id.CardViewsesion);
        String idRestaurante = "R001";
        ArrayList<Platillo> menu = new ArrayList<>();
        DatabaseReference mDatabase;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Sesion.class);
                startActivity(intent);
            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("platillos").child(idRestaurante).get().addOnSuccessListener(task -> {
            for (DataSnapshot categoria: task.getChildren()) {
                Platillo mCategoria = new Platillo();
                mCategoria.nombre = consultasSQL.categoría(idRestaurante,categoria.getKey(),"nombre_cat") ;
                mCategoria.viewType = 1;
                menu.add(mCategoria);
                for (DataSnapshot platillo: categoria.getChildren()) {
                    Platillo temp = platillo.getValue(Platillo.class);
                    temp.viewType = 2;
                    menu.add(temp);
                }
            }

            recyclerView.setAdapter(new MenuAdapter(menu));

        });





    }

    void CargarSlider() {
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(initR001());
    }


}