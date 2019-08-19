package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ModesActivity extends Activity implements OnClickListener {
	
	Button classicBtn, advancedBtn ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modes);
		classicBtn = (Button) findViewById(R.id.modes_classicBtn);
		advancedBtn = (Button) findViewById(R.id.modes_advancedBtn);
		classicBtn.setOnClickListener(this);
		advancedBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_modes, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.modes_classicBtn:
			Intent i = new Intent(ModesActivity.this, ClassicGameActivity.class);
		    startActivity(i);
			
			break;
		case R.id.modes_advancedBtn:
			Intent j = new Intent(ModesActivity.this, AdvancedGameActivity.class);
		    startActivity(j);
			
			break;

		default:
			break;
		}
		
	}

}
