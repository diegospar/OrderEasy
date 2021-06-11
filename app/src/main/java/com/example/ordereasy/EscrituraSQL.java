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



}
