/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Andrés
 */
public class Ruta {
    private ArrayList<Polyline> calles;
    private int idConductor;
    private int numeroPuestos;
    private String placaCarro;
    private String puntoSalida;
    private String puntoDestino;
    private String identificacion;
    private Date hora;
    private Date fecha;
    private long sesion;
    private long precio;

    public Ruta(ArrayList<Polyline> calles, int idConductor, int numeroPuestos, String placaCarro, String puntoSalida, String puntoDestino, String identificacion, Date hora, Date fecha, long sesion, long precio) {
        this.calles = calles;
        this.idConductor = idConductor;
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

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
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

    public long getSesion() {
        return sesion;
    }

    public void setSesion(long sesion) {
        this.sesion = sesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }
    
    
    
}
