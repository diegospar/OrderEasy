package com.example.ordereasy;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.concurrent.ExecutionException;

import static com.google.android.gms.tasks.Tasks.await;

public class ConsultasSQL {
        public String respuesta ="Categoría";
        public Object objeto;
        public Boolean existe;



/* Consultar categoría del restaurante

    *Campos disponibles*
    -HorarioF : Horario Final
    -HorarioI : Horario Inicial
    -Nombre: Nombre de la categoría
    -platillos : Número de platillos

 */
    public DataSnapshot categoría(String idRestaurante, String idCategoría, String campo) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return await(mDatabase.child("categorias").child(idRestaurante).child(idCategoría).child(campo).get());
    }


    public DataSnapshot getFood(String idRestaurante) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return await(mDatabase.child("platillos").child(idRestaurante).get());
    }

    public DataSnapshot getState(String idRestaurante, String idMesa) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        return await(mDatabase.child("mesas").child(idRestaurante).child(idMesa).child("estado").get());
    }


    public DataSnapshot getRestaurantName(String idRestaurante) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return await(mDatabase.child("restaurantes").child(idRestaurante).child("Nombre").get());
    }

    public DataSnapshot getSesion(String idRestaurante, String idMesa) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return await(mDatabase.child("sesiones").child(idRestaurante).child(idMesa).child("S001").get());
    }

    public DataSnapshot getOrderState(String idRestaurante, String idOrden) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return await(mDatabase.child("ordenes").child(idRestaurante).child(idOrden).get());
    }

    public DataSnapshot getPlatilloName(String idRestaurante, String idCategoria, String idPlatillo) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return await(mDatabase.child("platillos").child(idRestaurante).child(idCategoria).child(idPlatillo).child("nombre").get());
    }
    public DataSnapshot getTickets(String idRestaurante) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return await(mDatabase.child("tickets").child(idRestaurante).get());
    }

    public DataSnapshot getPlatillos(String idRestaurante) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return await(mDatabase.child("platillos").child(idRestaurante).get());
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

    public DataSnapshot existeLaOrden(String idRestaurante) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return await(mDatabase.child("ordenes").child(idRestaurante).get());
    }

    /*public String estadoOrden(String idRestaurante, String idOrden){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("ordenes").child(idRestaurante).child(idOrden).get();
    }*/

    public Query getSesionName(String idRestaurante, String idMesa){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return mDatabase.child("sesiones").child(idRestaurante).child(idMesa).child("S001").child("nombre").orderByKey();

    }
    public Query getOrden(String idRestaurante, String idOrden) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return mDatabase.child("ordenes").child(idRestaurante).child(idOrden).orderByKey();
    }

    public Query getSesionOrdenes(String idRestaurante, String idMesa){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return mDatabase.child("sesiones").child(idRestaurante).child(idMesa).child("S001").child("ordenes").orderByKey();

    }

    public Query getPlatillosRestaurante(String idRestaurante){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        return mDatabase.child("platillos").child(idRestaurante).orderByKey();

    }



}
