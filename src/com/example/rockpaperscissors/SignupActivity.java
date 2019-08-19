package com.example.rockpaperscissors;


import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends Activity implements OnClickListener {
	
	static SQLiteDatabase db=null;
	EditText nameInput,usernameInput, passwordInput, reEnterPasswordInput;
	Button signupBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		nameInput = (EditText) findViewById(R.id.nameInput);
		usernameInput = (EditText) findViewById(R.id.usernameInput);
		passwordInput = (EditText) findViewById(R.id.passwordInput);
		reEnterPasswordInput = (EditText) findViewById(R.id.reEnterPasswordInput);
		signupBtn = (Button) findViewById(R.id.signup);
		DbHelper dbh=new DbHelper(this, "score.db", null, 1);
		db=dbh.getWritableDatabase();
		signupBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_signup, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		String name = nameInput.getText().toString();
		String username = usernameInput.getText().toString();
		String password = passwordInput.getText().toString();
		String reEnterPassword = reEnterPasswordInput.getText().toString();
		if(password.equals(reEnterPassword)){
			try{
			cv.put("name", name);
			cv.put("username", username);
			cv.put("password", password);
			db.insert("userDetails", null, cv);
			}catch (SQLException e) {
				Toast.makeText(getApplicationContext(), "We are unable to process the request now. Please try again later or with a different username." , Toast.LENGTH_LONG).show();
			}finally{
				db.close();
			}
			Toast.makeText(getApplicationContext(), "User signup succesful" , Toast.LENGTH_LONG).show();
			
		}else{
			Toast.makeText(getApplicationContext(),"Your passwords doesn't match",Toast.LENGTH_SHORT).show();
			passwordInput.setText("");
			reEnterPasswordInput.setText("");
		}
		
	}

}
