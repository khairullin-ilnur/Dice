package com.example.dicegame;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ThrowPlayerFragment extends Fragment {

	TextView mPlayerName;
	ImageButton[] mDiceButtons;
	Button mPlayerCheatButton;
	Button mPlayerReadyButton;
	ArrayList<Dice> mCurrentRoundDices;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCurrentRoundDices = GameStorage.players.get(
				GameStorage.ROUND % 2 != 0 ? 0 : 1).getDices();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_throw_player, null);
		LinearLayout ll = (LinearLayout) v
				.findViewById(R.id.linearlayout_throw);

		mPlayerName = (TextView) v.findViewById(R.id.throw_player);
		mPlayerName.setText("Player " + String.valueOf(GameStorage.ROUND % 2 != 0 ? 1 : 2));

		mDiceButtons = new ImageButton[GameStorage.numberOfDice];
		for (int i = 0; i < mDiceButtons.length; i++) {
			mDiceButtons[i] = new ImageButton(getActivity());
			mDiceButtons[i].setImageResource(R.drawable.dice);
			mDiceButtons[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ImageButton btn = (ImageButton) v;
					if (btn.isActivated()) {
						btn.setActivated(false);
					} else {
						btn.setActivated(true);
					}
					checkButtons();
				}
			});
			ll.addView(mDiceButtons[i]);
		}

		mPlayerCheatButton = (Button) v
				.findViewById(R.id.button_throw_get_help);
		mPlayerCheatButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				for (int i = 0; i < mCurrentRoundDices.size(); i++) {
					if (Helper.isDiceLoaded(mCurrentRoundDices.get(i))) {
						list.add(i + 1);
					}
				}
				if (!list.isEmpty()) {
					Toast.makeText(getActivity(),
							"Шулерские кости: " + list.toString(),
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getActivity(), "Шулерских костей нет",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		mPlayerReadyButton = (Button) v.findViewById(R.id.button_throw_ok);
		mPlayerReadyButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < mDiceButtons.length; i++) {
					if (mDiceButtons[i].isActivated()) {
						GameStorage.currBadDices.add(i);
					}
				}
				Intent i = new Intent();
				i.setClass(getActivity(), RoundResultActivity.class);
				startActivity(i);
			}
		});

		return v;
	}

	private void checkButtons() {
		if (countActiveButtons() > GameStorage.badDice - 1) {
			for (int i = 0; i < mDiceButtons.length; i++) {
				if (!mDiceButtons[i].isActivated()) {
					mDiceButtons[i].setEnabled(false);
				}
			}
		}
		if (countActiveButtons() < GameStorage.badDice) {
			for (int i = 0; i < mDiceButtons.length; i++) {
				mDiceButtons[i].setEnabled(true);
			}
		}
	}

	private int countActiveButtons() {
		int count = 0;
		for (int i = 0; i < mDiceButtons.length; i++) {
			if (mDiceButtons[i].isActivated()) {
				count++;
			}
		}
		return count;
	}

}
