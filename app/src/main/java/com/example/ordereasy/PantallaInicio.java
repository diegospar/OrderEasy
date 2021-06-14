package com.example.ordereasy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.example.ordereasy.ConsultasSQL.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.List;

import static com.example.ordereasy.RestaurantesInicio.*;


public class PantallaInicio extends AppCompatActivity {

    private DatabaseReference mDatabase;
    public Orden orden;
    ConsultasSQL consultas = new ConsultasSQL();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_inicio);
        ImageView img = findViewById(R.id.imageView3);

       CargarSlider();
       CargarImagenes();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                escanear();
            }
        });
    }










 void CargarImagenes(){
     // ImageView in your Activity
     ImageView r1 = findViewById(R.id.Rest1);
     ImageView r2 = findViewById(R.id.Rest2);
     ImageView r3 = findViewById(R.id.Rest3);
     ImageView r4 = findViewById(R.id.Rest4);

// Download directly from StorageReference using Glide
// (See MyAppGlideModule for Loader registration)
     Glide.with(this /* context */)
             .load("https://firebasestorage.googleapis.com/v0/b/ordereasy-4510e.appspot.com" +
                     "/o/Restaurante1.jpg?alt=media&token=7fde6c7e-5b2f-4eff-830d-904c06d872e3")
             .into(r1);
// LLamada de la segunda imagen
     Glide.with(this /* context */)
             .load("https://firebasestorage.googleapis.com/v0/b/ordereasy-4510e.appspot.com" +
                     "/o/Restaurante2.jpg?alt=media&token=fdb0e9c7-1aec-4c54-8558-19ebc0d45890")
             .into(r2);
     //   Llamada de la tercera imagen
     Glide.with(this /* context */)
             .load("https://firebasestorage.googleapis.com/v0/b/ordereasy-4510e.appspot.com" +
                     "/o/Restaurante3.jpg?alt=media&token=ab45de0d-f4a5-4304-ae99-01e413da5bb8")
             .into(r3);
     //Llamada de la cuarta imagen
     Glide.with(this /* context */)
             .load("https://firebasestorage.googleapis.com/v0/b/ordereasy-4510e.appspot.com" +
                     "/o/Restaurante4.jpg?alt=media&token=091edaea-d9f1-4c35-b590-223a5974dc41")
             .into(r4);
 }

 void CargarSlider(){
     ImageSlider imageSlider= findViewById(R.id.image_slider);
     List<SlideModel> slideModels= new ArrayList<>();
     slideModels.add(new SlideModel(url1,"La mansion", ScaleTypes.FIT));
     slideModels.add(new SlideModel(url2,"La cocinilla",ScaleTypes.FIT));
     slideModels.add(new SlideModel(url3,"Coffe library", ScaleTypes.FIT));
     slideModels.add(new SlideModel(url4,"Pinea",ScaleTypes.FIT));
     slideModels.add(new SlideModel(url5,"NY exclusive", ScaleTypes.FIT));

     imageSlider.setImageList(slideModels);
 }





    public void escanear(){
        IntentIntegrator intent = new IntentIntegrator(this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("ESCANEAR CÓDIGO QR");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setBarcodeImageEnabled(false);
        intent.setOrientationLocked(false);
        intent.setCaptureActivity(CaptureActivityPortrait.class);
        intent.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        new Thread(() -> {
            try {
                if (result != null) {
                    if (result.getContents() == null) {
                        runOnUiThread(() -> {
                            Toast.makeText(PantallaInicio.this, "CANCELASTE EL ESCANEO", Toast.LENGTH_SHORT).show();
                        });


                    } else {
                        if (result.getContents().substring(0, 4).equals("OREA")) {
                            Intent intent = new Intent(this, Registro.class);
                            intent.putExtra("estado", consultas.getState(restauranteQR(result.getContents()), mesaQR(result.getContents())).getValue().toString()); //ocupada
                            intent.putExtra("restauranteQR", restauranteQR(result.getContents()).toString());
                            intent.putExtra("mesaQR", mesaQR(result.getContents()).toString());

                            startActivity(intent);
                        } else {
                            runOnUiThread(() -> {
                                Toast.makeText(PantallaInicio.this, "CÓDIGO INVÁLIDO", Toast.LENGTH_SHORT).show();
                            });


                        }

                    }

                } else {
                    super.onActivityResult(requestCode, resultCode, data);

                }
                runOnUiThread(() -> {

                });


            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }
    public boolean estaOcupado(String restaurante, String mesa){

        if(Integer.parseInt(consultas.mesa(restaurante,mesa,"estado")) == 1){
            return false;
        }else
            return true;

    }

    public String restauranteQR(String codigoQR){
        return codigoQR.substring(4,8);
    }

    public String mesaQR(String codigoQR){
        return codigoQR.substring(8);
    }

    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }

}