package com.example.ordereasy;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EscrituraSQL {
    public int idOrden;


    public void mesa(String restaurante, String mesa, String campo, String valor){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("mesas").child(restaurante).child(mesa).child(campo).setValue(valor);

    }

    public void orden(String restaurante, Orden orden){
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("ordenes").child(restaurante).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                idOrden = 500;
            }
            else {
               idOrden = Integer.parseInt(String.valueOf(task.getResult().getChildrenCount() + 1));
                mDatabase.child("ordenes").child(restaurante).child(String.valueOf(idOrden)).setValue(orden);
            }
        });



    }



}
