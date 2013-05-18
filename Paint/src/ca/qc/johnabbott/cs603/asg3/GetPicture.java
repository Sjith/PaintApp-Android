package ca.qc.johnabbott.cs603.asg3;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import ca.qc.johnabbott.cs603.asg3.shapes.Shape;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class GetPicture extends AsyncTask<String, String, String> {

	private String username;
	private String filename;
	private DrawingView drawing;
	private Context context;
	
	@Override
	protected void onCancelled() {
		super.onCancelled();
		Toast.makeText(context, "No file was found, will create one when saved under user " + username + "as file " + filename  , Toast.LENGTH_LONG).show();
	}

	public GetPicture(String username, String filename, DrawingView drawing, Context context) {
		super();
		this.username = username;
		this.filename = filename;
		this.drawing = drawing;
		this.context = context;
	}	
	@SuppressWarnings("unchecked")
	@Override
	protected String doInBackground(String... params) {
		try {
			URL url = new URL(new URL("http://10.0.2.2:9999/"), username + "/" + filename); // setups the connection to the server
			URLConnection urlConnection = url.openConnection();
			urlConnection.setDoInput(true);
			
			ObjectInputStream objectInputStream = new ObjectInputStream(urlConnection.getInputStream()); // gets the picture from the server
			Object object = objectInputStream.readObject(); 
			
			drawing.erase();
			ArrayList<Shape> shapes = (object != null) ? (ArrayList<Shape>) object : new ArrayList<Shape>(); // cast the object recieved into an array
			objectInputStream.close();
			drawing.getPicture().setShapesList(shapes);
	
		} catch (IOException e) {
			e.printStackTrace();
			cancel(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		drawing.invalidate();
		//Toast.makeText(context, "The file" + filename + " was loaded from user" + username, Toast.LENGTH_LONG).show();
	}
}
