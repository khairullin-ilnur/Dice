package com.example.dicegame;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class RoundResultFragment extends Fragment {

	TableLayout mTableResult;
	TextView mTextRound;
	TextView mTextScore;
	Button mNextButton;
	ArrayList<Integer> mResultList;
	
	public static final String TAG = "RoundResultFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Shooter shooter = new Shooter(GameStorage.players.get(
				GameStorage.ROUND % 2 != 0 ? 0 : 1).getDices());
		mResultList = shooter.roll();
		Log.d(TAG, mResultList.toString());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_result_round, null);
		mTableResult = (TableLayout) v.findViewById(R.id.table_result);

		TableRow diceRow = new TableRow(getActivity());
		TableRow resRow = new TableRow(getActivity());

		for (int i = 0; i < mResultList.size(); i++) {
			ImageView dice = new ImageView(getActivity());
			dice.setImageResource(convertResToImage(mResultList.get(i)));
			diceRow.addView(dice);
			ImageView res = new ImageView(getActivity());
			if (Integer.valueOf(i).equals(GameStorage.currBadDices.peek())) {
				res.setBackgroundResource(R.drawable.nope);
				GameStorage.currBadDices.poll();
			} else {
				res.setBackgroundResource(R.drawable.yes);
				GameStorage.players.get(GameStorage.ROUND % 2 != 0 ? 0 : 1).setScore(mResultList.get(i));
			}
			resRow.addView(res);
		}
		mTableResult.addView(diceRow);
		mTableResult.addView(resRow);
		
		mNextButton = (Button) v.findViewById(R.id.button_next_round);
		mNextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GameStorage.ROUND++;
				Intent i = new Intent();
				i.setClass(getActivity(), ChoosePlayerActivity.class);
				startActivity(i);
				
			}
		});
		return v;
	}

	private int convertResToImage(int res) {
		switch (res) {
		case 1:
			return R.drawable.one;
		case 2:
			return R.drawable.two;
		case 3:
			return R.drawable.three;
		case 4:
			return R.drawable.four;
		case 5:
			return R.drawable.five;
		case 6:
			return R.drawable.six;
		}
		return (Integer) null;
	}

}
