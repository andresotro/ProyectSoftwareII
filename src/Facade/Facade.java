/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Clases.Polyline;
import Clases.Ruta;
import Clases.UsuarioFactory;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Andrés
 */
public class Facade {
    private ArrayList<Ruta> rutas;
    private ArrayList<Long> sesiones;
    UsuarioFactory u;
    private static Facade instance;
    
    public static Facade getInstance() {
        if (instance == null) {
            instance = new Facade();
        }
        return instance;
    }
    
    public void registrarRuta(int IDConductor, int numeroPuestos, String placaCarro, String puntoSalida, String puntoDestino, String identificacion, Date hora, Date fecha, ArrayList<Polyline> calles, long sesion, long precio){
        Ruta r = new Ruta(calles, IDConductor, numeroPuestos, placaCarro, puntoSalida, puntoDestino, identificacion, hora, fecha, sesion, precio);
        rutas.add(r);
    }
    
    public ArrayList<Ruta> listarRuta(long sesion){
        ArrayList<Ruta> devolver = new ArrayList<>();
        for(Ruta r : rutas){
            if(r.getSesion() == sesion){
                devolver.add(r);
            }
        }
        return devolver;
    }
    
    public boolean eliminarRuta(long sesion, Date hora, Date fecha){
        boolean eliminado = false;
        for(Ruta r : rutas){
            if(r.getSesion() == sesion){
                if(r.getFecha() == fecha && r.getHora() == hora){
                    rutas.remove(r);
                    eliminado = true;
                    break;
                }
            }
        }
        return eliminado;
    }
    
    public boolean actualizarRuta(long sesion, Date horaBuscada, Date fechaBuscada, int numeroPuestos, String placaCarro, Date hora, float precio){
        boolean actualizado = false;
        for(Ruta r : rutas){
            if(r.getSesion() == sesion){
                if(r.getFecha() == fechaBuscada && r.getHora() == horaBuscada){
                    r.setNumeroPuestos(numeroPuestos);
                    r.setPlacaCarro(placaCarro);
                    r.setHora(hora);
                    r.setPrecio(sesion);
                    actualizado = true;
                }
            }
        }
        return actualizado;
    }
    
    public void guardarSesion(long sesion){
        sesiones.add(sesion);
    }
}
