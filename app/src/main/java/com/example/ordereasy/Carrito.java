package com.example.ordereasy;

public class Carrito {
    String precio;
    String nombre;
    String id;
    String cat;
    public String cant;

    Carrito(String nombre, String precio, String id, String categoria){
        this.precio = precio;
        this.nombre = nombre;
        this.id = id;
        this.cat = categoria;
        this.cant= "1";

    }
}
