 package com.example.ordereasy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.ordereasy.RestaurantesInicio.initR001;

public class Menu extends AppCompatActivity {

    Bundle bundle;
    ConsultasSQL consultasSQL = new ConsultasSQL();
    EscrituraSQL escrituraSQL = new EscrituraSQL();
    EditText observaciones, cantidad1, cantidad2, cantidad3, cantidad4, cantidad5,
                cantidad6, cantidad7, cantidad8, cantidad9, cantidad10;
    CardView cardView;
    TextView nombrePlatillo1, precio1,nombrePlatillo2, precio2,nombrePlatillo3, precio3,
            nombrePlatillo4, precio4, nombrePlatillo5, precio5, nombrePlatillo6, precio6,
            nombrePlatillo7, precio7, nombrePlatillo8, precio8, nombrePlatillo9, precio9,
            nombrePlatillo10, precio10, actualiza;
    TextView errorMessage, totalText, mensajeVacio;
    Button confirmarOrden;
    ProgressBar progressBar;
    ImageView carrito, atras;
    View principal, resumen, platillo1, platillo2, platillo3, platillo4, platillo5, platillo6,
            platillo7, platillo8, platillo9, platillo10;
    OrdenFirebase ordenFirebase = new OrdenFirebase();
    public static Orden orden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        CargarSlider();
        RecyclerView  recyclerView = findViewById(R.id.recyclerView);
        bundle= getIntent().getExtras();
        orden = new Orden(bundle.getString("restauranteQR"));
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
        actualiza = findViewById(R.id.actualiza);

        platillo1 =findViewById(R.id.platillo1);
        precio1 = platillo1.findViewById(R.id.precio);
        nombrePlatillo1 = platillo1.findViewById(R.id.nombrePlatillo);
        cantidad1 = platillo1.findViewById(R.id.cantidad);

        platillo2 =findViewById(R.id.platillo2);
        precio2 = platillo2.findViewById(R.id.precio);
        nombrePlatillo2 = platillo2.findViewById(R.id.nombrePlatillo);
        cantidad2 = platillo2.findViewById(R.id.cantidad);

        platillo3 =findViewById(R.id.platillo3);
        precio3 = platillo3.findViewById(R.id.precio);
        nombrePlatillo3 = platillo3.findViewById(R.id.nombrePlatillo);
        cantidad3 = platillo3.findViewById(R.id.cantidad);

        platillo4 =findViewById(R.id.platillo4);
        precio4 = platillo4.findViewById(R.id.precio);
        nombrePlatillo4 = platillo4.findViewById(R.id.nombrePlatillo);
        cantidad4 = platillo4.findViewById(R.id.cantidad);

        platillo5 =findViewById(R.id.platillo5);
        precio5 = platillo5.findViewById(R.id.precio);
        nombrePlatillo5 = platillo5.findViewById(R.id.nombrePlatillo);
        cantidad5 = platillo5.findViewById(R.id.cantidad);

        platillo6=findViewById(R.id.platillo6);
        precio6 = platillo6.findViewById(R.id.precio);
        nombrePlatillo6 = platillo6.findViewById(R.id.nombrePlatillo);
        cantidad6 = platillo6.findViewById(R.id.cantidad);

        platillo7 =findViewById(R.id.platillo7);
        precio7 = platillo7.findViewById(R.id.precio);
        nombrePlatillo7 = platillo7.findViewById(R.id.nombrePlatillo);
        cantidad7 = platillo7.findViewById(R.id.cantidad);

        platillo8 =findViewById(R.id.platillo8);
        precio8 = platillo8.findViewById(R.id.precio);
        nombrePlatillo8 = platillo8.findViewById(R.id.nombrePlatillo);
        cantidad8 = platillo8.findViewById(R.id.cantidad);

        platillo9 =findViewById(R.id.platillo9);
        precio9 = platillo9.findViewById(R.id.precio);
        nombrePlatillo9 = platillo9.findViewById(R.id.nombrePlatillo);
        cantidad9 = platillo9.findViewById(R.id.cantidad);

        platillo10 =findViewById(R.id.platillo10);
        precio10 = platillo10.findViewById(R.id.precio);
        nombrePlatillo10 = platillo10.findViewById(R.id.nombrePlatillo);
        cantidad10 = platillo10.findViewById(R.id.cantidad);



        String idRestaurante = "R001";
        ArrayList<Platillo> menu = new ArrayList<>();
        carrito.setOnClickListener(v -> {
            platillo1.setVisibility(View.GONE);
            platillo2.setVisibility(View.GONE);
            platillo3.setVisibility(View.GONE);
            platillo4.setVisibility(View.GONE);
            platillo5.setVisibility(View.GONE);
            platillo6.setVisibility(View.GONE);
            platillo7.setVisibility(View.GONE);
            platillo8.setVisibility(View.GONE);
            platillo9.setVisibility(View.GONE);
            platillo10.setVisibility(View.GONE);
            observaciones.setVisibility(View.GONE);
            totalText.setVisibility(View.GONE);
            orden.total="0";

            if(!orden.carrito.isEmpty()){
                observaciones.setVisibility(View.VISIBLE);
                mensajeVacio.setVisibility(View.GONE);
                totalText.setVisibility(View.VISIBLE);
                confirmarOrden.setVisibility(View.VISIBLE);
                if(orden.carrito.size() == 1){
                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);

                    precio1.setText("$" +orden.carrito.get(0).precio);
                    totalText.setText("$" + orden.carrito.get(0).precio);
                    orden.total = String.valueOf(orden.carrito.get(0).precio);
                   cantidad1.addTextChangedListener(new TextWatcher() {
                       @Override
                       public void onTextChanged(CharSequence s, int start, int before, int count) {
                       }
                       @Override
                       public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                       }
                       @Override
                       public void afterTextChanged(Editable s) {
                           if(!orden.carrito.isEmpty()) {
                               int total = Integer.parseInt(orden.carrito.get(0).precio);
                               if (!s.toString().equals("")) {
                                   if (Integer.parseInt(s.toString()) == 0) {
                                       cantidad1.setText("1");
                                       orden.carrito.get(0).cant = "1";
                                   }else {
                                   orden.carrito.get(0).cant = s.toString();}
                                   for (int i = 1; i < Integer.parseInt(orden.carrito.get(0).cant); i++) {
                                       total = total + Integer.parseInt(orden.carrito.get(0).precio);
                                   }
                                   precio1.setText("$" + String.valueOf(total));
                                   orden.total = String.valueOf(total);
                                   totalText.setText("Total: $" + String.valueOf(total));
                               }
                           }
                       }
                   });

                }else if (orden.carrito.size() == 2){

                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);
                    cantidad1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==2) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(0).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad1.setText("1");
                                        orden.carrito.get(0).cant = "1";
                                    }else {
                                    orden.carrito.get(0).cant = s.toString();}
                                    for (int i = 1; i < Integer.parseInt(orden.carrito.get(0).cant); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(0).precio);
                                    }
                                    precio1.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo2.setVisibility(View.VISIBLE);
                    nombrePlatillo2.setText(orden.carrito.get(1).nombre);
                    precio2.setText("$" + orden.carrito.get(1).precio);
                    cantidad2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==2) {
                                int total = Integer.parseInt(orden.carrito.get(1).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad2.setText("1");  orden.carrito.get(1).cant = "1";
                                    }else {
                                    orden.carrito.get(1).cant = s.toString();}
                                    for (int i = 1; i < Integer.parseInt(orden.carrito.get(1).cant); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(1).precio);
                                    }
                                    precio2.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });
                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant));
                    orden.total= String.valueOf(suma);
                    totalText.setText("Total: $" + String.valueOf(suma));

                }else if (orden.carrito.size() == 3){

                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);
                    cantidad1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==3) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(0).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad1.setText("1");
                                        orden.carrito.get(0).cant = "1";
                                    }else {
                                    orden.carrito.get(0).cant = s.toString();}
                                    for (int i = 1; i < Integer.parseInt(orden.carrito.get(0).cant); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(0).precio);
                                    }
                                    precio1.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo2.setVisibility(View.VISIBLE);
                    nombrePlatillo2.setText(orden.carrito.get(1).nombre);
                    precio2.setText("$" + orden.carrito.get(1).precio);
                    cantidad2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==3) {// igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(1).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad2.setText("1");
                                    }
                                    orden.carrito.get(1).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(1).precio);
                                    }
                                    precio2.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo3.setVisibility(View.VISIBLE);
                    nombrePlatillo3.setText(orden.carrito.get(2).nombre);
                    precio3.setText("$" + orden.carrito.get(2).precio);
                    cantidad3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==3) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(2).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad3.setText("1");
                                    }
                                    orden.carrito.get(2).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(2).precio);
                                    }
                                    precio3.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant));
                    orden.total= String.valueOf(suma);
                    totalText.setText("Total: $" + String.valueOf(suma));

                }else if(orden.carrito.size()==4){

                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);
                    cantidad1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==4) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(0).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad1.setText("1");
                                    }
                                    orden.carrito.get(0).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(0).precio);
                                    }
                                    precio1.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo2.setVisibility(View.VISIBLE);
                    nombrePlatillo2.setText(orden.carrito.get(1).nombre);
                    precio2.setText("$" + orden.carrito.get(1).precio);
                    cantidad2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==4) { // igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(1).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad2.setText("1");
                                    }
                                    orden.carrito.get(1).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(1).precio);
                                    }
                                    precio2.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo3.setVisibility(View.VISIBLE);
                    nombrePlatillo3.setText(orden.carrito.get(2).nombre);
                    precio3.setText("$" + orden.carrito.get(2).precio);
                    cantidad3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==4) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(2).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad3.setText("1");
                                    }
                                    orden.carrito.get(2).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(2).precio);
                                    }
                                    precio3.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo4.setVisibility(View.VISIBLE);
                    nombrePlatillo4.setText(orden.carrito.get(3).nombre);
                    precio4.setText("$" + orden.carrito.get(3).precio);
                    cantidad4.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==4) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(3).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad4.setText("1");
                                    }
                                    orden.carrito.get(3).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(3).precio);
                                    }
                                    precio4.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant));
                    orden.total= String.valueOf(suma);
                    totalText.setText("Total: $" + String.valueOf(suma));

                }else if(orden.carrito.size()==5){
                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);
                    cantidad1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==5) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(0).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad1.setText("1");
                                    }
                                    orden.carrito.get(0).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(0).precio);
                                    }
                                    precio1.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo2.setVisibility(View.VISIBLE);
                    nombrePlatillo2.setText(orden.carrito.get(1).nombre);
                    precio2.setText("$" + orden.carrito.get(1).precio);
                    cantidad2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==5) { // igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(1).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad2.setText("1");
                                    }
                                    orden.carrito.get(1).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(1).precio);
                                    }
                                    precio2.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo3.setVisibility(View.VISIBLE);
                    nombrePlatillo3.setText(orden.carrito.get(2).nombre);
                    precio3.setText("$" + orden.carrito.get(2).precio);
                    cantidad3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==5) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(2).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad3.setText("1");
                                    }
                                    orden.carrito.get(2).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(2).precio);
                                    }
                                    precio3.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo4.setVisibility(View.VISIBLE);
                    nombrePlatillo4.setText(orden.carrito.get(3).nombre);
                    precio4.setText("$" + orden.carrito.get(3).precio);
                    cantidad4.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==5) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(3).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad4.setText("1");
                                    }
                                    orden.carrito.get(3).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(3).precio);
                                    }
                                    precio4.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo5.setVisibility(View.VISIBLE);
                    nombrePlatillo5.setText(orden.carrito.get(4).nombre);
                    precio5.setText("$" + orden.carrito.get(4).precio);
                    cantidad5.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==5) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(4).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad5.setText("1");
                                    }
                                    orden.carrito.get(4).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(4).precio);
                                    }
                                    precio5.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant));
                    orden.total= String.valueOf(suma);
                    totalText.setText("Total: $" + String.valueOf(suma));

                }else if(orden.carrito.size()==6){

                    platillo1.setVisibility(View.VISIBLE);
                    nombrePlatillo1.setText(orden.carrito.get(0).nombre);
                    precio1.setText("$" + orden.carrito.get(0).precio);
                    cantidad1.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==6) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(0).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad1.setText("1");
                                    }
                                    orden.carrito.get(0).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(0).precio);
                                    }
                                    precio1.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant)) +
                                            (Integer.parseInt(orden.carrito.get(5).precio)* Integer.parseInt(orden.carrito.get(5).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo2.setVisibility(View.VISIBLE);
                    nombrePlatillo2.setText(orden.carrito.get(1).nombre);
                    precio2.setText("$" + orden.carrito.get(1).precio);
                    cantidad2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==6) { // igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(1).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad2.setText("1");
                                    }
                                    orden.carrito.get(1).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(1).precio);
                                    }
                                    precio2.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant)) +
                                            (Integer.parseInt(orden.carrito.get(5).precio)* Integer.parseInt(orden.carrito.get(5).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo3.setVisibility(View.VISIBLE);
                    nombrePlatillo3.setText(orden.carrito.get(2).nombre);
                    precio3.setText("$" + orden.carrito.get(2).precio);
                    cantidad3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==6) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(2).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad3.setText("1");
                                    }
                                    orden.carrito.get(2).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(2).precio);
                                    }
                                    precio3.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant)) +
                                            (Integer.parseInt(orden.carrito.get(5).precio)* Integer.parseInt(orden.carrito.get(5).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo4.setVisibility(View.VISIBLE);
                    nombrePlatillo4.setText(orden.carrito.get(3).nombre);
                    precio4.setText("$" + orden.carrito.get(3).precio);
                    cantidad4.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==6) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(3).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad4.setText("1");
                                    }
                                    orden.carrito.get(3).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(3).precio);
                                    }
                                    precio4.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant)) +
                                            (Integer.parseInt(orden.carrito.get(5).precio)* Integer.parseInt(orden.carrito.get(5).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    platillo5.setVisibility(View.VISIBLE);
                    nombrePlatillo5.setText(orden.carrito.get(4).nombre);
                    precio5.setText("$" + orden.carrito.get(4).precio);
                    cantidad5.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                            if(orden.carrito.size()==6) { //va igual que el else if
                                int total = Integer.parseInt(orden.carrito.get(4).precio);
                                if (!s.toString().equals("")) {
                                    if (Integer.parseInt(s.toString()) == 0) {
                                        cantidad5.setText("1");
                                    }
                                    orden.carrito.get(4).cant = s.toString();
                                    for (int i = 1; i < Integer.parseInt(s.toString()); i++) {
                                        total = total + Integer.parseInt(orden.carrito.get(4).precio);
                                    }
                                    precio5.setText("$" + String.valueOf(total));
                                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant)) +
                                            (Integer.parseInt(orden.carrito.get(5).precio)* Integer.parseInt(orden.carrito.get(5).cant));
                                    orden.total = String.valueOf(suma);
                                    totalText.setText("Total: $" + String.valueOf(suma));
                                }
                            }
                        }
                    });

                    int suma = (Integer.parseInt(orden.carrito.get(0).precio) * Integer.parseInt(orden.carrito.get(0).cant))+
                            (Integer.parseInt(orden.carrito.get(1).precio)* Integer.parseInt(orden.carrito.get(1).cant)) +
                            (Integer.parseInt(orden.carrito.get(2).precio)* Integer.parseInt(orden.carrito.get(2).cant)) +
                            (Integer.parseInt(orden.carrito.get(3).precio)* Integer.parseInt(orden.carrito.get(3).cant)) +
                            (Integer.parseInt(orden.carrito.get(4).precio)* Integer.parseInt(orden.carrito.get(4).cant)) +
                            (Integer.parseInt(orden.carrito.get(5).precio)* Integer.parseInt(orden.carrito.get(5).cant));
                    orden.total= String.valueOf(suma);
                    totalText.setText("Total: $" + String.valueOf(suma));

                }else if(orden.carrito.size()==7){

                }else if(orden.carrito.size()==8){

                }else if(orden.carrito.size()==9){

                }else if(orden.carrito.size()==10){

                }

            }else {
                platillo1.setVisibility(View.GONE);
                platillo2.setVisibility(View.GONE);
                platillo3.setVisibility(View.GONE);
                platillo4.setVisibility(View.GONE);
                platillo5.setVisibility(View.GONE);
                platillo6.setVisibility(View.GONE);
                platillo7.setVisibility(View.GONE);
                platillo8.setVisibility(View.GONE);
                platillo9.setVisibility(View.GONE);
                platillo10.setVisibility(View.GONE);

                cantidad1.setText("1");
                cantidad2.setText("1");
                cantidad3.setText("1");
                cantidad4.setText("1");
                cantidad5.setText("1");
                cantidad6.setText("1");
                cantidad7.setText("1");
                cantidad8.setText("1");
                cantidad9.setText("1");
                cantidad10.setText("1");


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
            finish();
        });


        new Thread(() -> {
            try {
                runOnUiThread(() -> {
                    progressBar.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                });
                DataSnapshot data = consultasSQL.getFood(idRestaurante);
                menu.clear();
                for (DataSnapshot category: data.getChildren()) {
                    DataSnapshot categoryData = consultasSQL.categoría(idRestaurante, category.getKey(),"nombre_cat") ;
                    Platillo mCategoria = new Platillo();
                    mCategoria.nombre = categoryData.getValue().toString();
                    mCategoria.idCategoria = category.getKey().toString();
                    mCategoria.viewType = 1;
                    menu.add(mCategoria);

                    for (DataSnapshot platillo: category.getChildren()) {
                        Platillo tempFood = platillo.getValue(Platillo.class);
                        tempFood.idCategoria=mCategoria.idCategoria;
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
                escrituraSQL.sesion(restauranteQR,mesaQR, "ordenes",
                        consultasSQL.getSesion(restauranteQR, mesaQR).child("ordenes").getValue().toString()+ orden.id);
                escrituraSQL.sesion(restauranteQR,mesaQR, "total",
                        String.valueOf(Integer.parseInt(consultasSQL.getSesion(restauranteQR, mesaQR).
                                child("total").getValue().toString()) + Integer.parseInt(orden.total)));
                orden = new Orden(bundle.getString("restauranteQR"));
                orden.ordenToFirebase(ordenFirebase);

                runOnUiThread(() -> {
                    principal.setVisibility(View.VISIBLE);
                    resumen.setVisibility(View.GONE);
                    observaciones.setVisibility(View.GONE);
                    platillo1.setVisibility(View.GONE);
                    platillo2.setVisibility(View.GONE);
                    platillo3.setVisibility(View.GONE);
                    platillo4.setVisibility(View.GONE);
                    platillo5.setVisibility(View.GONE);
                    platillo6.setVisibility(View.GONE);
                    platillo7.setVisibility(View.GONE);
                    platillo8.setVisibility(View.GONE);
                    platillo9.setVisibility(View.GONE);
                    platillo10.setVisibility(View.GONE);
                    confirmarOrden.setVisibility(View.GONE);
                    totalText.setVisibility(View.GONE);

                    for (Platillo p : menu) {
                        p.added = true;
                    }

                    Toast.makeText(Menu.this, "Orden Confirmada", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Menu.this, Sesion.class);
                    intent.putExtra("mesaQR", mesaQR);
                    intent.putExtra("restauranteQR",restauranteQR);
                    startActivity(intent);
                    finish();
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


    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

}