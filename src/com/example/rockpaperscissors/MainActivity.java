package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
			 i = new Intent(MainActivity.this, ModesActivity.class);
		    startActivity(i); 
			break;
		case R.id.rules:
			 i = new Intent(MainActivity.this, RulesActivity.class);
		    startActivity(i); 
			break;
		case R.id.scores:
			 i = new Intent(MainActivity.this, ScoresActivity.class);
		    startActivity(i); 
			break;

		default:
			break;
		}
	}

}
