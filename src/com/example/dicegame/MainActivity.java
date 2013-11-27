package com.example.dicegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	EditText mNumberOfDice;
	EditText mNumberOfBadDice;
	Button mPlayButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mNumberOfDice = (EditText) findViewById(R.id.text_dice_number);
		mNumberOfDice.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				GameStorage.numberOfDice = Integer.valueOf(s.toString());
				GameStorage.players.add(new Player(GameStorage.numberOfDice));
				GameStorage.players.add(new Player(GameStorage.numberOfDice));
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		
		mNumberOfBadDice = (EditText) findViewById(R.id.text_dice_number_change);
		mNumberOfBadDice.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				GameStorage.badDice = Integer.parseInt(s.toString());
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	
	
		mPlayButton = (Button) findViewById(R.id.button_play_new_game);
		final Intent i = new Intent(MainActivity.this, ChoosePlayerActivity.class);
		mPlayButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(i);
				
			}
		});
		
	}

}
