/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.echo;

import java.util.ArrayList;
import java.util.Random;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/**
 * The Echo API which Endpoints will be exposing.
 */
// [START echo_api_annotation]
@Api(
    name = "Proxy",
    version = "v1",
    namespace =
    @ApiNamespace(
        ownerDomain = "echo.example.com",
        ownerName = "echo.example.com",
        packagePath = ""
    ),
    // [START_EXCLUDE]
    issuers = {
        @ApiIssuer(
            name = "firebase",
            issuer = "https://securetoken.google.com/YOUR-PROJECT-ID",
            jwksUri =
                "https://www.googleapis.com/service_accounts/v1/metadata/x509/securetoken@system"
                    + ".gserviceaccount.com"
        )
    }
// [END_EXCLUDE]
)
// [END echo_api_annotation]
public class Proxy implements IProxy{
    
    private static Proxy unicaInstancia;
    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    
    public static Proxy reemplazarConstructora(){
        if(unicaInstancia == null){
            unicaInstancia = new Proxy();
        }
        return unicaInstancia;
    }
    
 // [START echo_method]
    @ApiMethod(name = "IniciarSesion")
    @Override
    public long iniciarSesion(String correo, String contraseña){
        long devolver = 0;
        Usuario x = new Conductor();
        x.setCorreo("Micasa");x.setNombre("Yo");x.setPassword("123");x.setTipoUsuario("Conductor");
        listaUsuarios.add(x);
        x.setCorreo("Empoin");x.setNombre("Isiempoin");x.setPassword("Nosequehacer");x.setTipoUsuario("Conductor");
        listaUsuarios.add(x);
        for(Usuario u : listaUsuarios){
            if(u.getCorreo().equals(correo) && u.getPassword().equals(contraseña)){
                Random rand = new Random(); // generate a random number
                devolver = rand.nextInt(9999) + 1;
                Facade f = Facade.getInstance();
                f.guardarSesion(devolver, correo);
            }
        }
        return devolver;
    }
 // [END echo_method]
    
    public String verificarUsuario(String correo, String contraseña){
        String devolver = "No";
        for(Usuario u : listaUsuarios){
            if(u.getCorreo().equals(correo) && u.getPassword().equals(contraseña)){
                devolver = u.getTipoUsuario();
            }
        }
        return devolver;
    }
    
}
