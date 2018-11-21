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

import java.util.ArrayList;
import java.util.Date;
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
     * Test of getInstance method, of class Facade.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Facade expResult = null;
        Facade result = Facade.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarRuta method, of class Facade.
     */
    @Test
    public void testRegistrarRuta() {
        System.out.println("registrarRuta");
        String correo = "";
        int numeroPuestos = 0;
        String placaCarro = "";
        String puntoSalida = "";
        String puntoDestino = "";
        String tipoRuta = "";
        String identificacion = "";
        String horaS = "";
        String fechaS = "";
        float precio = 0.0F;
        Facade instance = new Facade();
        instance.registrarRuta(correo, numeroPuestos, placaCarro, puntoSalida, puntoDestino, tipoRuta, identificacion, horaS, fechaS, precio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarRuta method, of class Facade.
     */
    @Test
    public void testListarRuta() {
        System.out.println("listarRuta");
        String correo = "";
        Facade instance = new Facade();
        ArrayList<Ruta> expResult = null;
        ArrayList<Ruta> result = instance.listarRuta(correo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarRuta method, of class Facade.
     */
    @Test
    public void testEliminarRuta() {
        System.out.println("eliminarRuta");
        String correo = "";
        String hora = "";
        Date fecha = null;
        Facade instance = new Facade();
        instance.eliminarRuta(correo, hora, fecha);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarRuta method, of class Facade.
     */
    @Test
    public void testActualizarRuta() {
        System.out.println("actualizarRuta");
        String correo = "";
        String horaBuscada = "";
        Date fechaBuscada = null;
        int numeroPuestos = 0;
        String placaCarro = "";
        String hora = "";
        float precio = 0.0F;
        Facade instance = new Facade();
        instance.actualizarRuta(correo, horaBuscada, fechaBuscada, numeroPuestos, placaCarro, hora, precio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardarSesion method, of class Facade.
     */
    @Test
    public void testGuardarSesion() {
        System.out.println("guardarSesion");
        int sesion = 0;
        String correo = "";
        Facade instance = new Facade();
        instance.guardarSesion(sesion, correo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificarSesion method, of class Facade.
     */
    @Test
    public void testVerificarSesion() {
        System.out.println("verificarSesion");
        String correo = "";
        Facade instance = new Facade();
        Devolver expResult = null;
        Devolver result = instance.verificarSesion(correo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
