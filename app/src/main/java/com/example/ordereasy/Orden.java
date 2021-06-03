package com.example.ordereasy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Orden {

    public String platillos;
    public int estado;
    public String mesa;
    public String hora;

    Orden(){

    }

    public Orden(String platillos, int estado, String mesa, String hora){

        this.estado = estado;
        this.hora = hora;
        this.mesa = mesa;
        this.platillos = platillos;

    }



}
