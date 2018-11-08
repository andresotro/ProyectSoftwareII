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
public interface IUsuario {
    public String getNombre();
    public void setNombre(String nombre);
    public String getCorreo();
    public void setCorreo(String correo);
    public String getPassword();
    public void setPassword(String password);
    public void setTipo(String tipo);
    public String getTipo();
}
