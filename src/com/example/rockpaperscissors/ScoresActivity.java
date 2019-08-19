package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoresActivity extends Activity implements OnClickListener{

	static SQLiteDatabase db=null;
	TableLayout t;
	Button b;
	int count=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		DbHelper dbh=new DbHelper(this, "game.db", null, 1);
		db=dbh.getWritableDatabase();
		show();
	}
	private void show() {
		// TODO Auto-generated method stub
		Cursor res=db.rawQuery("select * from highscore where username='"+SigninActivity.current_username+"' order by score desc", null);
		
		 t=(TableLayout) findViewById(R.id.res);
		if(res.moveToFirst())
		{
			do
			{
				TableRow tr=new TableRow(getApplicationContext());
				TextView t1=new TextView(getApplicationContext());
				TextView t2=new TextView(getApplicationContext());
				
				
				String ename=res.getString(res.getColumnIndex("name"));;
				int escore=res.getInt(res.getColumnIndex("score"));
				t1.setText(ename+"      ");
				t2.setText(escore+"");
				
				t1.setTextColor(Color.BLACK);
				t2.setTextColor(Color.BLACK);
				
				tr.addView(t1);
				tr.addView(t2);
				
				t.addView(tr);
				
				count++;
			}
			while(res.moveToNext()&&count<5);
			count=0;
		}
		

		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//String str="truncate table highscore";
		//db.execSQL(str);
		count=0;
		db.delete("highscore", null, null);
		t.removeAllViews();
		
	}

	
}
