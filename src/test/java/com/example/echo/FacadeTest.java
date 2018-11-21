/*
 * Copyright 2018 Felipe Montoya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.echo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Felipe Montoya
 */
public class FacadeTest {
    
    public FacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }



    /**
     * Test of registrarRuta method, of class Facade.
     */
    @Test
    public void testRegistrarRuta() {
        System.out.println("registrarRuta");
        String correo = "Prueba";
        int numeroPuestos = 5;
        String placaCarro = "BPC-352";
        String puntoSalida = "56";
        String puntoDestino = "78";
        String tipoRuta = "";
        String identificacion = "154698321";
        String horaS = "12:00:00";
        String fechaS = "2018-03-03";
        float precio = 3500;
        Facade instance = new Facade();
        instance.registrarRuta(correo, numeroPuestos, placaCarro, puntoSalida, puntoDestino, tipoRuta, identificacion, horaS, fechaS, precio);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of listarRuta method, of class Facade.
     */
    @Test
    public void testListarRuta() {
        System.out.println("listarRuta");
        String correo = "Prueba";
        Facade instance = new Facade();
        instance.registrarRuta("Prueba", 5, "BPC-335", "54", "65", "", "123456", "12:00:00", "2018-03-03", 3500);
        ArrayList<Ruta> devolver = new ArrayList<>();
        devolver = instance.listarRuta(correo);
        String expResult = "BPC-335";
        String result = devolver.get(0).getPlacaCarro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
      //  fail("The test case is a prototype.");
    }
    

    /**
     * Test of eliminarRuta method, of class Facade.
     */
    
    @Test
    public void testEliminarRuta() {
        System.out.println("eliminarRuta");
        String correo = "Prueba";
        String hora = "12:00:00";
        SimpleDateFormat cosa = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
             fecha = cosa.parse("2018-03-03");
        } catch (ParseException ex) {
            Logger.getLogger(FacadeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Facade instance = new Facade();
        instance.registrarRuta("Prueba", 5, "BPC-335", "54", "65", "", "123456", "12:00:00", "2018-03-03", 3500);
        instance.eliminarRuta(correo, hora, fecha);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarRuta method, of class Facade.
     */
    @Test
    public void testActualizarRuta() {
        System.out.println("actualizarRuta");
        ArrayList<Ruta> devolver = new ArrayList<>();
        String correo = "Prueba";
        String fechaS = "2018-03-03";
        SimpleDateFormat cosa = new SimpleDateFormat("yyyy-MM-dd");
        int numeroPuestos = 7;
        String placaCarro = "LOL-555";
        String hora = "12:00:00";
        float precio = 3400;
        Facade instance = new Facade();
        instance.registrarRuta(correo, 7, "BPC-335", "54", "65", "", "123456", "12:00:00", fechaS, 3400);
        devolver = instance.listarRuta(correo);
        Date fechaBuscada = devolver.get(0).getFecha();
        String horaBuscada = devolver.get(0).getHora();
        instance.actualizarRuta(correo, horaBuscada, fechaBuscada, numeroPuestos, placaCarro, hora, precio);        
        devolver = instance.listarRuta(correo);
        String expResult = devolver.get(0).getPlacaCarro();
        assertEquals(expResult, placaCarro);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of guardarSesion method, of class Facade.
     */
    @Test
    public void testGuardarSesion() {
        System.out.println("guardarSesion");
        int sesion = 3555;
        String correo = "Prueba3";
        Facade instance = new Facade();
        instance.guardarSesion(sesion, correo);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of verificarSesion method, of class Facade.
     */
    @Test
    public void testVerificarSesion() {
        System.out.println("verificarSesion");
        String correo = "Prueba3";
        Facade instance = new Facade();
        instance.guardarSesion(3555, correo);
        String expResult = "3555";
        String result = instance.verificarSesion(correo).getInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
  
}
