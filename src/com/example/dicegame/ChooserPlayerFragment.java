package com.example.dicegame;

import java.util.Arrays;

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

	public static String TAG = "com.example.dicegame.ChooserPlayerFragment";

	TextView mPlayerName;
	Player mPlayer;
	Button mPlayerOk;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		if (MainGame.ROUND % 2 != 0) {
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
		mPlayerName.setText("Player " + MainGame.ROUND % 2);

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
					Dice dice = mPlayer.getDices().get(
							Integer.parseInt(idTextFields[i].getText()
									.toString()) - 1);
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
					dice.set(h);
				}
				for (int i = 0; i < GameStorage.badDice; i++) {
					Log.d(TAG,
							Arrays.toString(mPlayer.getDices().get(i)
									.getVerge()));
				}

			}
		});

		return v;
	}

}
