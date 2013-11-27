package com.example.dicegame;

import java.util.ArrayList;

import android.util.Log;

public class Helper {

	public static final String TAG = "Helper";

	private static final int selection = 100;
	private static double sqrDeviation;
	
	static {
		
	}

	public static boolean isDiceLoaded(Dice d) {
		Shooter shooter = new Shooter(d);
		ArrayList<Integer> selectionList = new ArrayList<Integer>();
		double average = 0;
		double sDeviation = 0;
		for (int i = 0; i < selection; i++) {
			selectionList.addAll(shooter.roll());
		}
		for (Integer i : selectionList) {
			average = average + i;
		}
		average = average * 1.0 / selection;
		for (Integer i : selectionList) {
			sDeviation = sDeviation + Math.pow((i - average), 2);
		}
		sDeviation = Math.sqrt(sDeviation / (selection - 1));
		int counter = 0;
		for (Integer i : selectionList) {
			if ((i < average - 3 * sDeviation)
					|| (i > average + 3 * sDeviation)) {
				counter++;
			}
		}
		Log.d(TAG, "Average is " + average + ", Deviation is " + sDeviation
				+ ", Counter is " + counter);
		Log.d(TAG, selectionList.toString());
		return counter > 3 ? true : false;
	}
	/* public static boolean isDiceLoaded(Dice d) {
		double[] v = d.getVerge();
		double c = 0.0001;
		for (int i = 0; i < v.length - 1; i++) {
			if (Math.abs(v[i] - v[i+1]) > c)
				return true;
		}
		return false;
	} */
}
