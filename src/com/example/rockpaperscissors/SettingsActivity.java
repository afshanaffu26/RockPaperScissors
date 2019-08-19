package com.example.rockpaperscissors;

import android.media.AudioManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingsActivity extends Activity implements OnSeekBarChangeListener,OnClickListener {
	
	AudioManager audioManager;
	EditText numberOfRounds;
	TextView edit;
	TextView roundNumber;
	Button done;
	String play_no_of_rounds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		SeekBar volumeSlider = (SeekBar) findViewById(R.id.volumeSlider);
		numberOfRounds = (EditText) findViewById(R.id.numberOfRounds);
		done = (Button) findViewById(R.id.done);
		numberOfRounds.setVisibility(View.INVISIBLE);
		edit = (TextView) findViewById(R.id.edit);
		edit.setOnClickListener(this);
		done.setOnClickListener(this);
		done.setVisibility(View.INVISIBLE);
		roundNumber = (TextView) findViewById(R.id.roundNumber);
		roundNumber.setOnClickListener(this);
		volumeSlider.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
		volumeSlider.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)); 
        volumeSlider.setOnSeekBarChangeListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_settings, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress, 0);
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.edit:
			roundNumber.setVisibility(View.INVISIBLE);
			numberOfRounds.setVisibility(View.VISIBLE);
			done.setVisibility(View.VISIBLE);
			edit.setVisibility(View.INVISIBLE);
			break;
		case R.id.done:
			roundNumber.setText(numberOfRounds.getText());
			roundNumber.setVisibility(View.VISIBLE);
			numberOfRounds.setVisibility(View.INVISIBLE);
			done.setVisibility(View.INVISIBLE);
			edit.setVisibility(View.VISIBLE);
			break;
			
		}
	}

}
