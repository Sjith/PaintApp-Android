package ca.qc.johnabbott.cs603.asg3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.content.Context;
import android.os.AsyncTask;

public class PostPicture extends AsyncTask<String, String, String> {
	private String username;
	private String filename;
	private DrawingView drawing;

	
	
	public PostPicture(String username, String filename, DrawingView drawing, Context context) {
		super();
		this.username = username;
		this.filename = filename;
		this.drawing = drawing;
	}

	@Override
	protected String doInBackground(String... params) {
		try {
			URL url = new URL(new URL("http://10.0.2.2:9999/"), username + "/" + filename);// setups the connection to the server
			URLConnection urlConnection = url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(urlConnection.getOutputStream()); // serializes the object and writes to the server
			objectOutputStream.writeObject(drawing.getPicture().getShapesList());
			objectOutputStream.flush();
			objectOutputStream.close();
			
			String response;
		    BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); // gets response from the server
		    while ((response = reader.readLine()) != null) {
		      System.out.println(response);
		    }
		    reader.close();
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
	    catch (IOException e) {
	    	e.printStackTrace();
	    	return null;
	    }
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		drawing.erase();
		drawing.invalidate();
	}

}
