package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ModesActivity extends Activity implements OnClickListener {
	
	Button classicBtn, advancedBtn ;
	static SQLiteDatabase db=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modes);
		DbHelper dbh = new DbHelper(this, "game.db", null, 1);
        db = dbh.getWritableDatabase();
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
			int noOfWins = 0;
			Cursor res = db.rawQuery("select * from classicWins where username='"+SigninActivity.current_username+"'", null);
	        if (res.moveToFirst()) {
	             noOfWins = Integer.parseInt(res.getString(res.getColumnIndex("classicwins")));
        	}
	        if(noOfWins > 3) {
	        	Intent j = new Intent(ModesActivity.this, AdvancedGameActivity.class);
			    startActivity(j);
	        }
	        else {
	        	Toast.makeText(getApplicationContext(), "You need to win "+(3-noOfWins)+" classic games to unlock advanced game", Toast.LENGTH_LONG).show();
	        }
			
			break;

		default:
			break;
		}
		
	}

}
