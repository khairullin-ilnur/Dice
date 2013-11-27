package com.example.dicegame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

public class ThrowPlayerActivity extends SingleFragmentActivity {

	TextView mPlayersScore;
	TextView mRound;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_game);

		mRound = (TextView) findViewById(R.id.text_number_round);
		mRound.setText(String.valueOf(GameStorage.ROUND));
		
		mPlayersScore = (TextView)findViewById(R.id.text_number_score);
		mPlayersScore.setText(GameStorage.players.get(0).getScore() + " - " + GameStorage.players.get(1).getScore());
	}

	@Override
	protected Fragment createFragment() {
		return new ThrowPlayerFragment();
	}

}
