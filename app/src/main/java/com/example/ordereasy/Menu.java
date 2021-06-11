 package com.example.ordereasy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    EscrituraSQL escrituraSQL = new EscrituraSQL();
    EditText observaciones, cantidad1, cantidad2, cantidad3, cantidad4;
    CardView cardView;
    TextView nombrePlatillo1, precio1,nombrePlatillo2, precio2,nombrePlatillo3, precio3,
            nombrePlatillo4, precio4, nombrePlatillo5, precio5, nombrePlatillo6, precio6,
            nombrePlatillo7, precio7, nombrePlatillo8, precio8, nombrePlatillo9, precio9,
            nombrePlatillo10, precio10;
    TextView errorMessage, totalText, mensajeVacio;
    Button confirmarOrden;
    ProgressBar progressBar;
    ImageView carrito, atras;
    View principal, resumen, platillo1, platillo2, platillo3, platillo4;
    OrdenFirebase ordenFirebase = new OrdenFirebase();
    public static Orden orden =new Orden();

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
        carrito = findViewById(R.id.imageView6);
        principal = findViewById(R.id.layoutPrincipal);
        resumen = findViewById(R.id.layoutCarrito);
        atras = findViewById(R.id.botonAtras);
        confirmarOrden = findViewById(R.id.confirmaOrden);
        totalText = findViewById(R.id.total);
        mensajeVacio = findViewById(R.id.mensajeVacio);
        observaciones = findViewById(R.id.editTextObservaciones);

        platillo1 =findViewById(R.id.platillo1);
        precio1 = platillo1.findViewById(R.id.precio);
        nombrePlatillo1 = platillo1.findViewById(R.id.nombrePlatillo);

        platillo2 =findViewById(R.id.platillo2);
        precio2 = platillo2.findViewById(R.id.precio);
        nombrePlatillo2 = platillo2.findViewById(R.id.nombrePlatillo);

        platillo3 =findViewById(R.id.platillo3);
        precio3 = platillo3.findViewById(R.id.precio);
        nombrePlatillo3 = platillo3.findViewById(R.id.nombrePlatillo);

        platillo4 =findViewById(R.id.platillo4);
        precio4 = platillo4.findViewById(R.id.precio);
        nombrePlatillo4 = platillo4.findViewById(R.id.nombrePlatillo);

        String idRestaurante = "R001";
        ArrayList<Platillo> menu = new ArrayList<>();
        carrito.setOnClickListener(v -> {
            if(!orden.carrito.isEmpty()){
                observaciones.setVisibility(View.VISIBLE);
                mensajeVacio.setVisibility(View.GONE);
                totalText.setVisibility(View.VISIBLE);
                confirmarOrden.setVisibility(View.VISIBLE);
                if(orden.carrito.size() == 1){
                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);
                    totalText.setText("Total: $" + orden.carrito.get(0).precio);

                }else if (orden.carrito.size() == 2){

                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);

                    platillo2.setVisibility(View.VISIBLE);
                    nombrePlatillo2.setText(orden.carrito.get(1).nombre);
                    precio2.setText("$" + orden.carrito.get(1).precio);
                    int suma = Integer.parseInt(orden.carrito.get(0).precio) +
                            Integer.parseInt(orden.carrito.get(1).precio);
                    totalText.setText("Total: $" + String.valueOf(suma));

                }else if (orden.carrito.size() == 3){

                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);

                    platillo2.setVisibility(View.VISIBLE);
                    nombrePlatillo2.setText(orden.carrito.get(1).nombre);
                    precio2.setText("$" + orden.carrito.get(1).precio);

                    platillo3.setVisibility(View.VISIBLE);
                    nombrePlatillo3.setText(orden.carrito.get(2).nombre);
                    precio3.setText("$" + orden.carrito.get(2).precio);

                    int suma = Integer.parseInt(orden.carrito.get(0).precio) +
                            Integer.parseInt(orden.carrito.get(1).precio) +
                            Integer.parseInt(orden.carrito.get(2).precio);
                    totalText.setText("Total: $" + String.valueOf(suma));

                }else {

                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);

                    platillo2.setVisibility(View.VISIBLE);
                    nombrePlatillo2.setText(orden.carrito.get(1).nombre);
                    precio2.setText("$" + orden.carrito.get(1).precio);

                    platillo3.setVisibility(View.VISIBLE);
                    nombrePlatillo3.setText(orden.carrito.get(2).nombre);
                    precio3.setText("$" + orden.carrito.get(2).precio);

                    platillo4.setVisibility(View.VISIBLE);
                    nombrePlatillo4.setText(orden.carrito.get(3).nombre);
                    precio4.setText("$" + orden.carrito.get(3).precio);

                    int suma = Integer.parseInt(orden.carrito.get(0).precio) +
                            Integer.parseInt(orden.carrito.get(1).precio) +
                            Integer.parseInt(orden.carrito.get(2).precio) +
                            Integer.parseInt(orden.carrito.get(3).precio);
                    totalText.setText("Total: $" + String.valueOf(suma));

                }

            }else {
                platillo1.setVisibility(View.GONE);
                platillo2.setVisibility(View.GONE);
                platillo3.setVisibility(View.GONE);
                platillo4.setVisibility(View.GONE);
                confirmarOrden.setVisibility(View.GONE);
                mensajeVacio.setVisibility(View.VISIBLE);
            }
            principal.setVisibility(View.GONE);
            resumen.setVisibility(View.VISIBLE);
        });
        atras.setOnClickListener(v -> {
            principal.setVisibility(View.VISIBLE);
            resumen.setVisibility(View.GONE);
        });
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(Menu.this, Sesion.class);
            String restauranteQR = bundle.getString("restauranteQR");
            String mesaQR = bundle.getString("mesaQR");
            intent.putExtra("mesaQR", mesaQR);
            intent.putExtra("restauranteQR",restauranteQR);
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
                    mCategoria.idCategoria = category.getKey().toString();
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
        String restauranteQR = bundle.getString("restauranteQR");
        String mesaQR = bundle.getString("mesaQR");

        confirmarOrden.setOnClickListener(v -> {
            new Thread(() -> {
            try {
                orden.idRestaurante = restauranteQR;
                orden.mesa = mesaQR;
                orden.registraOrden(orden);
                orden.observaciones = observaciones.getText().toString();
                orden.ordenToFirebase(ordenFirebase);
                escrituraSQL.orden(ordenFirebase, restauranteQR,ordenFirebase.id);
                orden.limpiarOrden();
                orden.ordenToFirebase(ordenFirebase);
                runOnUiThread(() -> {
                    principal.setVisibility(View.VISIBLE);
                    resumen.setVisibility(View.GONE);
                    Toast.makeText(Menu.this, "Orden Confirmada", Toast.LENGTH_LONG).show();
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();


        });
    }

   void CargarSlider() {
        ImageSlider imageSlider = findViewById(R.id.image_slider);
        imageSlider.setImageList(initR001());
    }




}