package com.example.ordereasy;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

@IgnoreExtraProperties
public class OrdenFirebase {

    public String platillos = ""; //C001P001, C001P015, C002P001
    public long estado;
    public String mesa;
    public String hora;
    public String fecha;
    public String observaciones;
    public String id;

    public OrdenFirebase(){

    }

}
