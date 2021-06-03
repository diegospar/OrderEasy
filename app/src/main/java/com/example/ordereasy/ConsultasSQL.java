package com.example.ordereasy;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ConsultasSQL {
        public String respuesta =".";
        public Object objeto;



/* Consultar categoría del restaurante

    *Campos disponibles*
    -HorarioF : Horario Final
    -HorarioI : Horario Inicial
    -Nombre: Nombre de la categoría
    -platillos : Número de platillos

 */
    public String categoría(String idRestaurante, String idCategoría, String campo){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("categorias").child(idRestaurante).child(idCategoría).child(campo).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                respuesta = "ERROR";
            }
            else {
                respuesta = task.getResult().getValue().toString();
            }
        });
         return respuesta;
    }

    /* Consultar restaurantes disponibles

        *Campos disponibles*
        Categorías: número de categorías.
        Enlace: enlace de la ubicación del restaurante.
        Mesas: número de mesas.
        Nombre: Nombre del restaurante


     */
    public String restaurante(String idRestaurante, String campo){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("restaurantes").child(idRestaurante).child(campo).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                respuesta = "ERROR";
            }
            else {
                respuesta = task.getResult().getValue().toString();
            }
        });
        return respuesta;
    }

    public String platillo(String idRestaurante, String idCategoría, String idPlatillo, String campo){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("platillos").child(idRestaurante).child(idCategoría).child(idPlatillo)
                .child(campo).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                respuesta = "ERROR";
            }
            else {
                respuesta = task.getResult().getValue().toString();
            }
        });
        return respuesta;
    }

    public String mesa(String idRestaurante, String idMesa,  String campo){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("mesas").child(idRestaurante).child(idMesa).child(campo)
                .get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                respuesta = "ERROR";
            }
            else {
                respuesta = task.getResult().getValue().toString();
                // return respuesta;
            }
        });
        return respuesta;
    }


    public String sesion(String idRestaurante, String idSesion,  String campo){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("sesiones").child(idRestaurante).child(idSesion).child(campo)
                .get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                respuesta = "ERROR";
            }
            else {
                respuesta = task.getResult().getValue().toString();
            }
        });
        return respuesta;
    }

    public Object prueba() {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("platillos").child("R001").get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                objeto = "ERROR";
            }
            else {
                //objeto = task.getResult().getValue(;
                for (DataSnapshot snapshot: task.getResult().getChildren()) {
                    //Job_Class job = jobSnapshot.getValue(Job_Class.class);
                    Log.d("Prueba", snapshot.getValue().toString());
                }
            }
        });
        return objeto;
    }
}
