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
public class Pasajero extends Usuario{
    public Pasajero(){
        super();
    }   
    
    @Override
    public String toString() {
        return "Correo: "+super.getCorreo()+" - Password: "+super.getPassword()+" - Tipo de Usuario: Conductor";
    }
}
