/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.echo;

/**
 *
 * @author Andrés
 */
public class Conjunto {
    private long sesion;
    private String correo;

    public Conjunto(long sesion, String correo) {
        this.sesion = sesion;
        this.correo = correo;
    }

    public long getSesion() {
        return sesion;
    }

    public void setSesion(long sesion) {
        this.sesion = sesion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
