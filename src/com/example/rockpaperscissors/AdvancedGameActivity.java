package com.example.rockpaperscissors;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class AdvancedGameActivity extends Activity implements OnClickListener {

    public enum Option {
        ROCK,
        PAPER,
        SCISSORS,
        LIZARD,
        SPOCK
    }

    public enum Result {
        WIN,
        LOSS,
        DRAW
    }
    private Option userSelection, androidSelection;
    int uc = 0;
    int ac = 0;
    Button bt1, bt2, bt3, bt4, bt5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_game);
        bt1 = (Button) findViewById(R.id.advanced_rock);
        bt2 = (Button) findViewById(R.id.advanced_paper);
        bt3 = (Button) findViewById(R.id.advanced_scissors);
        bt4 = (Button) findViewById(R.id.advanced_lizard);
        bt5 = (Button) findViewById(R.id.advanced_spock);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub




        ImageView imageView = (ImageView) findViewById(R.id.advanced_user);

        boolean play = true;

        switch (v.getId()) {
            case R.id.advanced_rock:
                userSelection = Option.ROCK;
                imageView.setImageResource(R.drawable.rock);
                break;
            case R.id.advanced_paper:
                userSelection = Option.PAPER;
                imageView.setImageResource(R.drawable.paper);
                break;
            case R.id.advanced_scissors:
                userSelection = Option.SCISSORS;
                imageView.setImageResource(R.drawable.scissors);
                break;
            case R.id.advanced_lizard:
                userSelection = Option.LIZARD;
                imageView.setImageResource(R.drawable.lizard);
                break;
            case R.id.advanced_spock:
                userSelection = Option.SPOCK;
                imageView.setImageResource(R.drawable.spock);
                break;

        }




        if (play) {
            play();
            if (uc == 10 || ac == 10)
                showResults();
        }
    }

    private void showResults() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AdvancedGameActivity.this);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing  
                ImageView imageView = (ImageView) findViewById(R.id.advanced_user);
                ImageView imageView1 = (ImageView) findViewById(R.id.advanced_android);
                imageView.setImageResource(R.drawable.images);
                imageView1.setImageResource(R.drawable.images);
                TextView userS = (TextView) findViewById(R.id.textView2);
                TextView androidS = (TextView) findViewById(R.id.textView3);
                uc = 0;
                ac = 0;
                userS.setText(String.valueOf("user score:" + uc));
                androidS.setText(String.valueOf("Computer Score:" + ac));
            }
        });

        // Sets the right message according to result.
        if (ac > uc) {
            builder.setMessage("You Loose!");
        } else if (ac < uc) {
            builder.setMessage("You Win!");
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
        int rand = random.nextInt(5);
        androidSelection = null;
        ImageView imageView = (ImageView) findViewById(R.id.advanced_android);

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
            case 3:
                androidSelection = Option.LIZARD;
                imageView.setImageResource(R.drawable.paper);
                break;
            case 4:
                androidSelection = Option.SPOCK;
                imageView.setImageResource(R.drawable.scissors);
                break;
        }
        // Determine game result according to user selection and Android selection.
        if (androidSelection == userSelection) {
            uc++;
            ac++;
        } else if (androidSelection == Option.ROCK && userSelection == Option.SCISSORS) {
            ac++;
        } else if (androidSelection == Option.ROCK && userSelection == Option.LIZARD) {
            ac++;
        } else if (androidSelection == Option.PAPER && userSelection == Option.ROCK) {
            ac++;
        } else if (androidSelection == Option.PAPER && userSelection == Option.SPOCK) {
            ac++;
        } else if (androidSelection == Option.SCISSORS && userSelection == Option.PAPER) {
            ac++;
        } else if (androidSelection == Option.SCISSORS && userSelection == Option.LIZARD) {
            ac++;
        } else if (androidSelection == Option.LIZARD && userSelection == Option.PAPER) {
            ac++;
        } else if (androidSelection == Option.LIZARD && userSelection == Option.SPOCK) {
            ac++;
        } else if (androidSelection == Option.SPOCK && userSelection == Option.ROCK) {
            ac++;
        } else if (androidSelection == Option.SPOCK && userSelection == Option.SCISSORS) {
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
        Intent back = new Intent(AdvancedGameActivity.this, HomePageActivity.class);
        startActivity(back);
        finish();

    }

}