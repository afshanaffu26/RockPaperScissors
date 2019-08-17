package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class RulesActivity extends Activity implements OnClickListener {
	
	TextView classicRules = (TextView) findViewById(R.id.classicRules);
	TextView advancedRules = (TextView) findViewById(R.id.advancedRules);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rules);
		advancedRules.setVisibility(View.INVISIBLE);
		classicRules.setVisibility(View.VISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_rules, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.classic:
			advancedRules.setVisibility(View.INVISIBLE);
			classicRules.setVisibility(View.VISIBLE);
			break;
	case R.id.advanced:
		classicRules.setVisibility(View.INVISIBLE);
		advancedRules.setVisibility(View.VISIBLE);
		break;

		default:
			break;
		}
		
	}

}
