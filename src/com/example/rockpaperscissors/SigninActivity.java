package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SigninActivity extends Activity implements android.view.View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		Button signinBtn = (Button) findViewById(R.id.signin);
		signinBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_signin, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			 case R.id.signin :
				 Intent i = new Intent(SigninActivity.this, MainActivity.class);
				 startActivity(i); 
				break;
				  }
	}

}
