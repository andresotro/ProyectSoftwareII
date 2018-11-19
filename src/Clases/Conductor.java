/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author alejo
 */
public class Conductor extends Usuario{
    private String tipoUsuario;
    
    public Conductor(){
        super();
        tipoUsuario = "Conductor";
    }   
    
    @Override
    public String toString() {
        return "Correo: "+super.getCorreo()+" - Password: "+super.getPassword()+" - Tipo de Usuario: Conductor";
    }

    @Override
    public String getTipoUsuario() {
        return tipoUsuario;
    }
        
}
