package ca.qc.johnabbott.cs603.asg3;

import ca.qc.johnabbott.cs603.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText inputEditText;
	private Button fetchButton;
	private Button newButton;
	private String filename;
	private Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		fetchButton = (Button) findViewById(R.id.fetchButton);
		newButton = (Button) findViewById(R.id.newButton);
		inputEditText = (EditText) findViewById(R.id.usernameEditText);
		
		fetchButton.setOnClickListener(new View.OnClickListener() { // will fetch a new file on the server
			
			@Override
			public void onClick(View view) {
				acquireFilenameString("Enter Existing File Name", "Enter File Name", true);
			}
		});
		newButton.setOnClickListener(new View.OnClickListener() { // will create a new file on the server
			
			@Override
			public void onClick(View view) {
				acquireFilenameString("Enter New File Name", "Enter File Name", false);
			}
		});
	}

	private void acquireFilenameString(String title, String message, final Boolean fetch) { // ask user for the filename then launches the intent and send it the username and filename
		final String username = inputEditText.getText().toString();
		if ( username == null || username.equals(""))
			Toast.makeText(getApplicationContext(), "Enter a username!", Toast.LENGTH_LONG).show();
		else
		{
		final EditText input = new EditText(context);
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setTitle(title)
			 .setMessage(message)
			 .setView(input)
			 .setPositiveButton("Ok", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int button) {
					filename = input.getText().toString();
					if (filename.equals(""))
						Toast.makeText(getApplicationContext(),"Enter a filename!", Toast.LENGTH_LONG).show();
					else
					{
						Intent intent = new Intent(context, MainActivity.class);
						intent.putExtra("username",username);
						intent.putExtra("filename", filename);
						intent.putExtra("fetch", fetch);
						context.startActivity(intent);	
					}
				}
			 })
			 .setNegativeButton("Cancel", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int button) {} // cancel
			 })
			 .show();
		}
	}
	

}
