package com.example.ordereasy;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.example.ordereasy.RestaurantesInicio.*;

public class Menu extends AppCompatActivity {

    ConsultasSQL consulta = new ConsultasSQL();
    ArrayList<Platillo> platillos = new ArrayList<Platillo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        CargarSlider();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        platillos = consulta.menu("R001");

        recyclerView.setAdapter(new MenuAdapter(platillos));

    }

    void CargarSlider() {
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(initR001());
    }
}