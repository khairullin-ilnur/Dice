package com.example.dicegame;

public class Dice {
    private double[] verge;

    public Dice() {
        verge = new double[]{0.166, 0.166, 0.166, 0.166, 0.166};
    }

    public double[] getVerge() {
        return verge;
    }
    
    public void set(double[] d) {
    	this.verge = d;
    }
}
