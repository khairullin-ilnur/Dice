package com.example.dicegame;

import java.util.ArrayList;


public class Player {
    private int k_dice;
    private ArrayList<Dice> dices;
    private int score;

    public Player(int k_dice) {
        this.k_dice = k_dice;
        this.score = 0;
        this.dices = new ArrayList<Dice>();
        for (int i = 0; i < k_dice; i++) {
            dices.add(new Dice());
        }
    }

    public int getK_dice() {
        return k_dice;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Dice> getDices() {
        return dices;
    }
}
