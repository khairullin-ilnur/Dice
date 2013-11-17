package com.example.dicegame;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class MainGame extends SingleFragmentActivity {
	
	public static int ROUND = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	
	@Override
	protected Fragment createFragment() {
		return new ChooserPlayerFragment();
	}

}
