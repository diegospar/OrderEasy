package com.example.ordereasy;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.ExecutionException;

import static com.google.android.gms.tasks.Tasks.await;

public class EscrituraSQL {
    public int idOrden;


    public void mesa(String restaurante, String mesa, String campo, int valor){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        if (campo.equals("id_mesa")) {
            mDatabase.child("mesas").child(restaurante).child(mesa).child(campo).setValue(String.valueOf(valor));
        }else {mDatabase.child("mesas").child(restaurante).child(mesa).child(campo).setValue(valor);}

    }

    public void orden(OrdenFirebase orden, String idRestaurante, String ordenID) throws ExecutionException, InterruptedException {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
       await(mDatabase.child("ordenes").child(idRestaurante).child(ordenID).setValue(orden));
    }

    public void sesion(String idRestaurante, String idMesa, String campo, String valor){

        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("sesiones").child(idRestaurante).child(idMesa).child("S001").child(campo).setValue(valor);
    }

    public void atencionMesa(String restaurante, String mesa) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("mesas").child(restaurante).child(mesa).child("atencion").setValue(1);
    }

    public void eliminaOrden(String idRestaurante, String ordenID) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("ordenes").child(idRestaurante).child(ordenID).setValue(null);
    }

    public void remplazaOrdenes(String idRestaurante, String idMesa, String ordenes) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("sesiones").child(idRestaurante).child(idMesa).child("S001").child("ordenes").setValue(ordenes);
    }

    public void remplazaTotal(String idRestaurante, String idMesa, String total) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("sesiones").child(idRestaurante).child(idMesa).child("S001").child("total").setValue(total);
    }

    public void ticket(String idRestaurante, String idTicket, DatosTicket ticket) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("tickets").child(idRestaurante).child(idTicket).setValue(ticket);
    }

    public void efectivoMesa(String restaurante, String mesa) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("mesas").child(restaurante).child(mesa).child("pago_efectivo").setValue(1);
    }


}
