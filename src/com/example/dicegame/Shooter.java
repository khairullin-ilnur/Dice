package com.example.dicegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import android.util.Log;

public class Shooter {
	
	private Random randomizer = new Random();
	public static final String TAG = "Shooter";
	
	public ArrayList<Dice> dices;
	
	public Shooter(ArrayList<Dice> dices) {
		this.dices = dices;
	}
	public Shooter(Dice dice) {
		ArrayList<Dice> list = new ArrayList<Dice>();
		list.add(dice);
		this.dices = list;
	}
	
	public ArrayList<Integer> roll() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (Dice d: dices) {
			double rnd = randomizer.nextDouble();
			Log.d(TAG, String.valueOf(rnd));
			double[] verges = d.getVerge();
			Log.d(TAG, Arrays.toString(verges));
			double j = 0;
			for (int i = 0; i < verges.length; i++) {
				j = j + verges[i];
				if ((Double.compare(j, rnd) >= 0)) {
					result.add(i+1);
					break;
				}
			}
		}
		return result;
	}
	
}
