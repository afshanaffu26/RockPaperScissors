package com.example.rockpaperscissors;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends Activity implements OnClickListener {

    static SQLiteDatabase db = null;
    public static String current_username, current_name;
    EditText usernameInput, passwordInput;
    TextView signupText;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        DbHelper dbh = new DbHelper(this, "game.db", null, 1);
        db = dbh.getWritableDatabase();
        usernameInput = (EditText) findViewById(R.id.signin_usernameInput);
        passwordInput = (EditText) findViewById(R.id.signin_passwordInput);
        signupText = (TextView) findViewById(R.id.signin_signupText);
        Button signinBtn = (Button) findViewById(R.id.signin_signinBtn);
        signinBtn.setOnClickListener(this);
        signupText.setOnClickListener(this);
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
    	
    	case R.id.signin_signupText:
    		Intent i = new Intent(SigninActivity.this, SignUpActivity.class);
            startActivity(i);
			
			break;
    	
		case R.id.signin_signinBtn:
			
			try{
			String username = usernameInput.getText().toString();
			String userPassword = passwordInput.getText().toString();
	        res = db.rawQuery("select * from userDetails where username='"+username+"'", null);
	        if (res.moveToFirst()) {
	            String passwordStored = res.getString(res.getColumnIndex("password"));
	            String nameStored = res.getString(res.getColumnIndex("name"));
	            if (userPassword.equals(passwordStored)) {
	            	current_username = username;
	            	current_name = nameStored;
	                Intent j = new Intent(SigninActivity.this, HomePageActivity.class);
	                startActivity(j);
	            } else {
	            	current_username = "";
	            	current_name = "";
	                Toast.makeText(getApplicationContext(), "Username/Password combination entered is incorrect. Please try again.", Toast.LENGTH_LONG).show();
	            }
	        } else {
	        	current_username = "";
	        	current_name = "";
	            Toast.makeText(getApplicationContext(), "Seems like you are not a user yet. Please sign up", Toast.LENGTH_LONG).show();
	        }
			}catch(SQLException e){
				current_username = "";
				current_name = "";
				Toast.makeText(getApplicationContext(), "We are unable to process the request now. Please try again later." , Toast.LENGTH_LONG).show();
			}
			
			break;

		default:
			break;
		}
        
    }

}