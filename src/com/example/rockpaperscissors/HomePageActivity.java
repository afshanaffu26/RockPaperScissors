package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomePageActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		Button playBtn = (Button) findViewById(R.id.play);
		Button rulesBtn = (Button) findViewById(R.id.rules);
		Button scoresBtn = (Button) findViewById(R.id.scores);
		playBtn.setOnClickListener(this);
		rulesBtn.setOnClickListener(this);
		scoresBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.play:
			 i = new Intent(HomePageActivity.this, ModesActivity.class);
		    startActivity(i); 
			break;
		case R.id.rules:
			 i = new Intent(HomePageActivity.this, RulesActivity.class);
		    startActivity(i); 
			break;
		case R.id.scores:
			 i = new Intent(HomePageActivity.this, ScoresActivity.class);
		    startActivity(i); 
			break;

		default:
			break;
		}
	}

}
