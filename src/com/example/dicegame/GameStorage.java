package com.example.dicegame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameStorage {
	
	public static int ROUND = 1;
	
	public static ArrayList<Player> players;
	public static int badDice;
	public static Queue<Integer> currBadDices; 
	public static int numberOfDice;
	
	static {
		players = new ArrayList<Player>();
		currBadDices = new LinkedList<Integer>();
	}
	

}
