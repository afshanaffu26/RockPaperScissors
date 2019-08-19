package com.example.rockpaperscissors;

import java.util.Random;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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

       
    }

   

}