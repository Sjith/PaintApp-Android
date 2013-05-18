package ca.qc.johnabbott.cs603.asg3;

import java.io.Serializable;
import java.util.ArrayList;

import ca.qc.johnabbott.cs603.asg3.shapes.Shape;

public class Picture implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Shape> shapesList = new ArrayList<Shape>(); // Used to store the shapes to draw on the view.
	
	public void setShapesList(ArrayList<Shape> shapesList) {
		this.shapesList = shapesList;
	}

	public ArrayList<Shape> getShapesList() {
		return shapesList;
	}

	public void addShapesToList(Shape shape) {
		this.shapesList.add(shape);
	}
	
	public void erase() {
		shapesList = new ArrayList<Shape>(); // Erase all the shapes
	}
	
}
