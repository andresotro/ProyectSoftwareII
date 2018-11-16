/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.echo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Andrés
 */
public class Ruta {
    private ArrayList<Polyline> calles;
    private String correoConductor;
    private int numeroPuestos;
    private String placaCarro;
    private String puntoSalida;
    private String puntoDestino;
    private String identificacion;
    private Date hora;
    private Date fecha;
    private int sesion;
    private float precio;

    public Ruta(ArrayList<Polyline> calles, String correoConductor, int numeroPuestos, String placaCarro, String puntoSalida, String puntoDestino, String identificacion, Date hora, Date fecha, int sesion, float precio) {
        this.calles = calles;
        this.correoConductor = correoConductor;
        this.numeroPuestos = numeroPuestos;
        this.placaCarro = placaCarro;
        this.puntoSalida = puntoSalida;
        this.puntoDestino = puntoDestino;
        this.identificacion = identificacion;
        this.hora = hora;
        this.fecha = fecha;
        this.sesion = sesion;
        this.precio = precio;
    }
    
    

    public ArrayList<Polyline> getCalles() {
        return calles;
    }

    public void setCalles(ArrayList<Polyline> calles) {
        this.calles = calles;
    }

    public String getCorreoConductor() {
        return correoConductor;
    }

    public void setCorreoConductor(String correoConductor) {
        this.correoConductor = correoConductor;
    }

    public int getNumeroPuestos() {
        return numeroPuestos;
    }

    public void setNumeroPuestos(int numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getPuntoSalida() {
        return puntoSalida;
    }

    public void setPuntoSalida(String puntoSalida) {
        this.puntoSalida = puntoSalida;
    }

    public String getPuntoDestino() {
        return puntoDestino;
    }

    public void setPuntoDestino(String puntoDestino) {
        this.puntoDestino = puntoDestino;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public int getSesion() {
        return sesion;
    }

    public void setSesion(int sesion) {
        this.sesion = sesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
}
