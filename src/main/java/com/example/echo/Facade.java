/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.echo;

import java.util.ArrayList;
import java.util.Date;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiIssuer;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import java.text.*;

@Api(
    name = "facade",
    version = "v1",
    namespace =
    @ApiNamespace(
        ownerDomain = "echo.example.com",
        ownerName = "echo.example.com",
        packagePath = ""
    ),
    issuers = {
        @ApiIssuer(
            name = "firebase",
            issuer = "https://securetoken.google.com/proyectsoftwareii",
            jwksUri =
                "https://www.googleapis.com/service_accounts/v1/metadata/x509/securetoken@system"
                    + ".gserviceaccount.com"
        )
    }
// [END_EXCLUDE]
)

public class Facade {
    private ArrayList<Ruta> rutas = new ArrayList<>();
    private ArrayList<Conjunto> sesiones = new ArrayList<>();
    UsuarioFactory u;
    private static Facade instance;
    
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }
    
    @ApiMethod(name = "RegistrarRuta")
    public void registrarRuta(@Named("numeroPuestos") int numeroPuestos, @Named("placaCarro") String placaCarro, @Named("puntoSalida") String puntoSalida, @Named("puntoDestino") String puntoDestino, @Named("Identificacion") String identificacion, @Named("hora") String horaS, @Named("fecha") String fechaS, @Named("precio") float precio){
        ArrayList<Calle> calles = null;
    	String correo = "Prueba";
        SimpleDateFormat cosa = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        Date hora = null;
        try {
            fecha = cosa.parse(fechaS);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        SimpleDateFormat cosa2 = new SimpleDateFormat("HH:mm:ss");
        try {
            hora = cosa2.parse(horaS);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Ruta r = new Ruta(calles, correo, numeroPuestos, placaCarro, puntoSalida, puntoDestino, identificacion, hora, fecha, precio);
        rutas.add(r);
    }
    
    @ApiMethod(name = "listarRutas")
    public ArrayList<Ruta> listarRuta(@Named("correoConductor") String correo){
        ArrayList<Ruta> devolver = new ArrayList<>();
        for(Ruta r : rutas){
            if(r.getCorreoConductor().equals(correo)){
                devolver.add(r);
            }
        }
        return devolver;
    }
    
    @ApiMethod(name = "eliminarRuta")
    public void eliminarRuta(@Named("correoConductor") String correo, @Named("hora") Date hora, @Named("fecha") Date fecha){
        for(Ruta r : rutas){
            if(r.getCorreoConductor().equals(correo)){
                if(r.getFecha() == fecha && r.getHora() == hora){
                    rutas.remove(r);
                    break;
                }
            }
        }
    }
    
    @ApiMethod(name = "actualizarRuta")
    public void actualizarRuta(@Named("correoConductor") String correo, @Named("horaBuscada") Date horaBuscada, @Named("fechaBuscada") Date fechaBuscada, @Named("numeroPuestos") int numeroPuestos, @Named("placaCarro") String placaCarro, @Named("nuevaHora") Date hora, @Named("nuevoPrecio") float precio){
        for(Ruta r : rutas){
            if(r.getCorreoConductor().equals(correo)){
                if(r.getFecha() == fechaBuscada && r.getHora() == horaBuscada){
                    r.setNumeroPuestos(numeroPuestos);
                    r.setPlacaCarro(placaCarro);
                    r.setHora(hora);
                    r.setPrecio(precio);
                }
            }
        }
    }
    
    @ApiMethod(name = "guardarSesion")
    public void guardarSesion(@Named("sesion") int sesion, @Named("correoConductor") String correo){
        Conjunto c = new Conjunto(sesion, correo);
        sesiones.add(c);
    }
    
    @ApiMethod(name = "verificarSesion")
    public Devolver verificarSesion(@Named("correoConductor") String correo){
        Conjunto x = new Conjunto(1235, "Prueba");
        Conjunto y = new Conjunto(9786, "Empoin");
        sesiones.add(x);
        sesiones.add(y);
        int sesionb = 0;
        for(Conjunto c : sesiones){
            if(c.getCorreo().equals(correo)){
                sesionb = c.getSesion();
            }
        }
        String prueba = Integer.toString(sesionb);
        Devolver cosa = new Devolver(prueba);
        return cosa;
    }
}
