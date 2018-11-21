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
public class ProxyTest {
    
    public ProxyTest() {
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
     * Test of reemplazarConstructora method, of class Proxy.
     */
    @Test
    public void testReemplazarConstructora() {
        System.out.println("reemplazarConstructora");
        Proxy expResult = null;
        Proxy result = Proxy.reemplazarConstructora();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iniciarSesion method, of class Proxy.
     */
    @Test
    public void testIniciarSesion() {
        System.out.println("iniciarSesion");
        String correo = "";
        String password = "";
        Proxy instance = new Proxy();
        Sesion expResult = null;
        Sesion result = instance.iniciarSesion(correo, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificarUsuario method, of class Proxy.
     */
    @Test
    public void testVerificarUsuario() {
        System.out.println("verificarUsuario");
        String correo = "";
        String password = "";
        Proxy instance = new Proxy();
        tipoUsuario expResult = null;
        tipoUsuario result = instance.verificarUsuario(correo, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rellenarUsuarios method, of class Proxy.
     */
    @Test
    public void testRellenarUsuarios() {
        System.out.println("rellenarUsuarios");
        Proxy instance = new Proxy();
        instance.rellenarUsuarios();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
