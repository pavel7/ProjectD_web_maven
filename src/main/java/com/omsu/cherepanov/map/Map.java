/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omsu.cherepanov.Map;

/**
 * @author Павел
 */
public class Map {

    private int max_X;
    private int max_Y;
    private double map[][];

    public Map(int max_X, int max_Y) {
        this.max_X = max_X;
        this.max_Y = max_Y;
        map = new double[max_X][max_Y];
    }

}
