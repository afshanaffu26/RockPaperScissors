package com.example.rockpaperscissors;

import java.util.Random;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ClassicGameActivity extends Activity implements OnClickListener {

    public enum Option {
        ROCK,
        PAPER,
        SCISSORS
    }

    public enum Result {
        WIN,
        LOSS,
        DRAW
    }
    
    static SQLiteDatabase db=null;
    private Option userSelection, androidSelection;
    int uc = 0;
    int ac = 0;
    Button bt1, bt2, bt3;
    ImageButton bt4;
    Cursor res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_game);
        DbHelper dbh = new DbHelper(this, "game.db", null, 1);
        db = dbh.getWritableDatabase();
        bt1 = (Button) findViewById(R.id.rock);
        bt2 = (Button) findViewById(R.id.paper);
        bt3 = (Button) findViewById(R.id.scissors);
        bt4 = (ImageButton) findViewById(R.id.imageButtonHome);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        ImageView imageView = (ImageView) findViewById(R.id.user);

        boolean play = true;

        switch (v.getId()) {
            case R.id.rock:
                userSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rock);
                break;
            case R.id.paper:
                userSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.paper);
                break;
            case R.id.scissors:
                userSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissors);
                break;

            case R.id.imageButtonHome:
                Intent i = new Intent(ClassicGameActivity.this, HomePageActivity.class);
                startActivity(i); // To go home.
                play = false;
                finish();
                break;

        }




        if (play) {
            play();
            if (uc == 10 || ac == 10)
                showResults();
        }
    }

    private void showResults() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ClassicGameActivity.this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing  
            	try{  
            		String noOfWins = null;
            	if (ac < uc) { 
            	ContentValues cv=new ContentValues();
            	ContentValues cv1=new ContentValues();
        		cv.put("name", SigninActivity.current_name);
        		cv.put("score", uc);
        		cv.put("username", SigninActivity.current_username);
        		db.insert("highscore", null, cv);
        		res = db.rawQuery("select * from classicWins where username='"+SigninActivity.current_username+"'", null);
    	        if (res.moveToFirst()) {
    	             noOfWins = res.getString(res.getColumnIndex("classicwins"));
            	}
    	        cv1.put("classicwins", Integer.parseInt(noOfWins)+1);
    	        cv1.put("username", SigninActivity.current_username);
    	        db.insert("classicWins", null, cv1);
    	        Toast.makeText(getApplicationContext(), "Your scores are submitted successfully", Toast.LENGTH_LONG).show();
            	}
                ImageView imageView = (ImageView) findViewById(R.id.user);
                ImageView imageView1 = (ImageView) findViewById(R.id.android);
                imageView.setImageResource(R.drawable.images);
                imageView1.setImageResource(R.drawable.images);
                TextView userS = (TextView) findViewById(R.id.textView2);
                TextView androidS = (TextView) findViewById(R.id.textView3);
                userS.setText(String.valueOf("user score:" + uc));
                androidS.setText(String.valueOf("Computer Score:" + ac));
				uc = 0;
                ac = 0;
            	}catch (SQLException e) {
            		Toast.makeText(getApplicationContext(), "Couldn't submit your scores. Please try again.", Toast.LENGTH_LONG).show();
				}
                
            }
        });
        if (ac < uc) { 
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent k=new Intent(ClassicGameActivity.this, HomePageActivity.class);
				startActivity(k);
				finish();
				
			}
			
			
		});
        }

        // Sets the right message according to result.
        if (ac > uc) {
            builder.setMessage("You Loose!");
        } else if (ac < uc) {
            builder.setMessage("You Win! Want to submit your scores?");
        } else if (ac == uc) {
            builder.setMessage("It's a draw!");
        }

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void play() {
        // Generates a random play.
        TextView userS = (TextView) findViewById(R.id.textView2);
        TextView androidS = (TextView) findViewById(R.id.textView3);
        Random random = new Random();
        int rand = random.nextInt(3);
        androidSelection = null;
        ImageView imageView = (ImageView) findViewById(R.id.android);

        // Sets the right image according to random selection.
        switch (rand) {
            case 0:
                androidSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rock);
                break;
            case 1:
                androidSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.paper);
                break;
            case 2:
                androidSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissors);
                break;
        }
        // Determine game result according to user selection and Android selection.
        if (androidSelection == userSelection) {
            uc++;
            ac++;
        } else if (androidSelection == Option.ROCK && userSelection == Option.SCISSORS) {

            ac++;
        } else if (androidSelection == Option.PAPER && userSelection == Option.ROCK) {

            ac++;
        } else if (androidSelection == Option.SCISSORS && userSelection == Option.PAPER) {

            ac++;
        } else {

            uc++;
        }
        userS.setText(String.valueOf("user score:" + uc));
        androidS.setText(String.valueOf("Computer Score:" + ac));
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        Intent back = new Intent(ClassicGameActivity.this, HomePageActivity.class);
        startActivity(back);
        finish();

    }

}