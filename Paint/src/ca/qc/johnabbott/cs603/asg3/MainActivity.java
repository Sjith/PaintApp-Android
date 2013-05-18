//	Name:			Harley McPhee
//  Date:			4/11/2013
//	Course:			420-603-AB
//  Assignment: 	Assignment 4
//  Description:	An application to draw Curves, Ellipse, Lines, Rectangles, and Paths with a chosen colour and width.
//                  The user can also save it to the server, or retrieve a picture from a server
package ca.qc.johnabbott.cs603.asg3;

import ca.qc.johnabbott.cs603.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private DrawingView drawing;
	private Dialog current;
	private String username;
	private String filename;
	private Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		drawing = (DrawingView)this.findViewById(R.id.drawing_view);
		
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		Boolean fetch = extras.getBoolean("fetch");
		username = extras.getString("username");
		filename = extras.getString("filename");
		if (fetch)
		{
			new GetPicture(username, filename, drawing, context ).execute(); // fetch the picture from the server
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
		case R.id.menu_tools:
			showToolsDialog();
			return true;
		case R.id.menu_menu:
			showMenuDialog();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void showToolsDialog() {
		new ToolSettingsDialog(this, drawing.getToolBox()); 
	}

	private void showMenuDialog() {
		current = new Dialog(this);
		current.setContentView(R.layout.dialog_menu);
		current.setTitle("Menu");
		current.setCanceledOnTouchOutside(true);
		
		Button buttonErase = (Button) current.findViewById(R.id.buttonErase);
		Button buttonRestore = (Button) current.findViewById(R.id.buttonRestore);
		Button buttonSave = (Button) current.findViewById(R.id.buttonSave);
		
		buttonErase.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				drawing.erase();
				drawing.invalidate();
				current.dismiss();
			}
		});
		
		buttonSave.setOnClickListener(new View.OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				current.dismiss();
				confirmFileName();    
				
			}
		});
			
		buttonRestore.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				new GetPicture(username, filename, drawing, context ).execute();			
				current.dismiss();
			}
		});
		
		current.show();
	}
	private void confirmFileName () // used to confirm the filename when saving, user can change the filename if he wants
	{
	final EditText input = new EditText(context);
	AlertDialog.Builder alert = new AlertDialog.Builder(this);
	input.setText(filename);
	alert.setTitle("Save file")
		.setMessage("Picture will be saved under user: " + username)
		 .setView(input)
		 .setPositiveButton("Ok", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int button) {
				filename = input.getText().toString();
				if (filename.equals(""))
					Toast.makeText(getApplicationContext(),"Enter a filename!", Toast.LENGTH_LONG).show();
				else
					new PostPicture(username, filename, drawing, context ).execute();				
			}
		 })
		 .setNegativeButton("Cancel", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int button) {} // cancel
		 })
		 .show();
	}
	
}
