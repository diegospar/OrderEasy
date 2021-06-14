package com.example.ordereasy;

import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Orden {
    private ConsultasSQL consultasSQL= new ConsultasSQL();

    public String platillos = ""; //C001P001, C001P015, C002P001
    public ArrayList<Carrito> carrito = new ArrayList<Carrito>();
    public long estado;
    public String mesa;
    public String hora;
    public String fecha;
    public String observaciones;
    public String id;
    public String idRestaurante;
    public String total;

    public Orden(String idRestaurante){
        this.generaId(idRestaurante);
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

    public void generaId(String idRestaurante) {
        new Thread(() -> {
            try {
                int numeroAleatorio = ((int) Math.floor(Math.random() * (999 - 1 + 1) + 1));// Valor entre M y N, ambos incluidos.

                if (numeroAleatorio > 99) {
                    if (consultasSQL.existeLaOrden(idRestaurante).hasChild("O" + String.valueOf(numeroAleatorio))) {
                        generaId(idRestaurante);
                    } else this.id = "O" + String.valueOf(numeroAleatorio);
                } else if (numeroAleatorio > 9) {

                    if (consultasSQL.existeLaOrden(idRestaurante).hasChild("O0" + String.valueOf(numeroAleatorio))) {
                        generaId(idRestaurante);
                    } else this.id = "O0" + String.valueOf(numeroAleatorio);

                } else {

                    if (!consultasSQL.existeLaOrden(idRestaurante).hasChild("O" + String.valueOf(numeroAleatorio))) {
                        generaId(idRestaurante);
                    } else this.id = "O00" + String.valueOf(numeroAleatorio);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

    }

    public void agregarPlatillo(String nombre, String precio, String id, String categoria){
        Carrito agrega = new Carrito(nombre, precio, id, categoria);
        this.carrito.add(agrega);
    }

    public void eliminaPlatillo(String id, String cat){

        for (int i = 0; i < this.carrito.size(); i++) {
            if (this.carrito.get(i).id.equals(id) && this .carrito.get(i).cat.equals(cat)){
                this.carrito.remove(i);
            }

        }
    }



    public void registraOrden(Orden orden){
        orden.fecha = date();
        orden.hora = hour();
        for (int i= 0; i<orden.carrito.size(); i++ ) {
            if (orden.platillos.equals("")){
                orden.platillos = orden.carrito.get(i).cat + orden.carrito.get(i).id;
                if(Integer.parseInt(orden.carrito.get(i).cant)!=1){
                    for (int j = 1; j< Integer.parseInt(orden.carrito.get(i).cant); j++){
                    orden.platillos = orden.platillos + "," + orden.carrito.get(i).cat + orden.carrito.get(i).id;
                    }
                }
            }else{
            orden.platillos = orden.platillos + "," + orden.carrito.get(i).cat + orden.carrito.get(i).id;
                if(Integer.parseInt(orden.carrito.get(i).cant)!=1){
                    for (int j = 1; j< Integer.parseInt(orden.carrito.get(i).cant); j++){
                        orden.platillos = orden.platillos + "," + orden.carrito.get(i).cat + orden.carrito.get(i).id;
                    }
                }
            }
        }
        orden.estado = 0;


    }
    public String date(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateformatted = dateFormat.format(date);
        return dateformatted;

    }

    public String hour(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        String dateformatted = dateFormat.format(date);
        return dateformatted;

    }

    public void ordenToFirebase(OrdenFirebase ordenFirebase){
        ordenFirebase.estado = this.estado;
        ordenFirebase.fecha=this.fecha;
        ordenFirebase.hora = this.hora;
        ordenFirebase.mesa = this.mesa;
        ordenFirebase.observaciones = this.observaciones;
        ordenFirebase.id = this.id;
        ordenFirebase.platillos = this.platillos;
        ordenFirebase.total = this.total;

    }


}
