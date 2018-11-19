/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.IProxy;
import java.util.ArrayList;

/**
 *
 * @author Andrés
 */
public class Proxy implements IProxy{
    
    private static Proxy unicaInstancia;
    private ArrayList<String[]> listaUsuarios = new ArrayList<>();
    
    
    
    @Override
    public long iniciarSesion(String correo, String contraseña){
        long sesion = 0;
        return sesion;
    }
    
}
