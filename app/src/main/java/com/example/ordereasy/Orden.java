package com.example.ordereasy;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Orden {
    private ConsultasSQL consultasSQL= new ConsultasSQL();

    public String platillos = ""; //C001P001, C001P015, C002P001
    public int estado;
    public String mesa;
    public String hora;
    public String fecha;
    public String observaciones;
    public String id;

    Orden(){

    }

    public Orden(String platillos, int estado, String mesa, String hora){

        this.estado = estado;
        this.hora = hora;
        this.mesa = mesa;
        this.platillos = platillos;

    }

    public void agregaPlatillo(String idplatillo, String idcategoria){

        this.platillos = this.platillos + "" + idcategoria + idplatillo;
    }

    public void generaId(String idRestaurante){
        int numeroAleatorio = ((int) Math.floor(Math.random() * (999 - 1 + 1) + 1));// Valor entre M y N, ambos incluidos.

        if(numeroAleatorio>99){
            if (consultasSQL.existeLaOrden("O" + String.valueOf(numeroAleatorio), idRestaurante)){
                generaId(idRestaurante);
            } else this.id= "O" + String.valueOf(numeroAleatorio);
        }else if (numeroAleatorio > 9){

            if (consultasSQL.existeLaOrden("O0" + String.valueOf(numeroAleatorio), idRestaurante)){
                generaId(idRestaurante);
            } else this.id= "O0" + String.valueOf(numeroAleatorio);

        }else {

            if (consultasSQL.existeLaOrden("O00" + String.valueOf(numeroAleatorio), idRestaurante)){
                generaId(idRestaurante);
            } else this.id= "O00" + String.valueOf(numeroAleatorio);

        }

    }



}
