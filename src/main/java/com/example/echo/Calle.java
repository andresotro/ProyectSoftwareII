/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.echo;

import java.awt.Color;
import java.awt.Point;

/**
 *
 * @author Andrés
 */
public class Calle {
    private Point[] puntos;
    private int width;
    private Color color;

    public Calle() {
    
    }

    public Point[] getPuntos() {
        return puntos;
    }

    public void setPuntos(Point[] puntos) {
        this.puntos = puntos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
