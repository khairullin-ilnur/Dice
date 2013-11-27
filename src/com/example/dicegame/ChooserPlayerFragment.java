package com.example.dicegame;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooserPlayerFragment extends Fragment {

	public static final String TAG = "ChooserPlayerFragment";
	public static final String EXTRA_DICES = "EXTRA_DICES";

	TextView mPlayerName;
	Player mPlayer;
	Button mPlayerOk;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (GameStorage.ROUND % 2 != 0) {
			mPlayer = GameStorage.players.get(0);
		} else {
			mPlayer = GameStorage.players.get(1);
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_choose_player, null);
		LinearLayout ll = (LinearLayout) v
				.findViewById(R.id.linearlayout_chooser);

		mPlayerName = (TextView) v.findViewById(R.id.chooser_player);
		int player = GameStorage.ROUND % 2 != 0 ? 1: 2;
		mPlayerName.setText("Player " + player);

		final EditText[] idTextFields = new EditText[GameStorage.badDice];
		final EditText[] idVergeTextFields = new EditText[GameStorage.badDice];
		final EditText[] percentTextFields = new EditText[GameStorage.badDice];

		for (int i = 0; i < GameStorage.badDice; i++) {
			idTextFields[i] = new EditText(getActivity());
			idTextFields[i].setHint("Номер кубика");
			ll.addView(idTextFields[i]);
			idVergeTextFields[i] = new EditText(getActivity());
			idVergeTextFields[i].setHint("Грань");
			ll.addView(idVergeTextFields[i]);
			percentTextFields[i] = new EditText(getActivity());
			percentTextFields[i].setHint("Вероятность");
			ll.addView(percentTextFields[i]);
		}

		mPlayerOk = (Button) v.findViewById(R.id.button_chooser_ok);
		mPlayerOk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				for (int i = 0; i < GameStorage.badDice; i++) {
					double[] h = new double[6];
					for (int j = 0; j < h.length; j++) {
						if (j == Integer.parseInt(idVergeTextFields[i]
								.getText().toString()) - 1) {
							h[Integer.parseInt(idVergeTextFields[i].getText()
									.toString()) - 1] = Double
									.parseDouble(percentTextFields[i].getText()
											.toString());
						} else {
							h[j] = (1.0 - Double
									.parseDouble(percentTextFields[i].getText()
											.toString())) / 5;
						}
					}
					mPlayer.getDices()
							.get(Integer.parseInt(idTextFields[i].getText()
									.toString()) - 1).set(h);
					GameStorage.players.set(GameStorage.ROUND % 2 != 0? 0 : 1, mPlayer);
				}
				for (int i = 0; i < GameStorage.numberOfDice; i++) {
					Log.d(TAG,
							Arrays.toString(GameStorage.players.get(GameStorage.ROUND % 2 != 0 ? 0 : 1).getDices().get(i)
									.getVerge()));
				}
				Intent intent = new Intent();
				intent.setClass(getActivity(), ThrowPlayerActivity.class);
				startActivity(intent);
			}
		});

		return v;
	}

}
