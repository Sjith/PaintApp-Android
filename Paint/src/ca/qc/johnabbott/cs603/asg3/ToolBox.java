package ca.qc.johnabbott.cs603.asg3;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import ca.qc.johnabbott.cs603.asg3.tools.CurveTool;
import ca.qc.johnabbott.cs603.asg3.tools.EllipseTool;
import ca.qc.johnabbott.cs603.asg3.tools.LineTool;
import ca.qc.johnabbott.cs603.asg3.tools.PathTool;
import ca.qc.johnabbott.cs603.asg3.tools.RectangleTool;
import ca.qc.johnabbott.cs603.asg3.tools.Tool;
import ca.qc.johnabbott.cs603.asg3.tools.ToolName;

public class ToolBox {

	private int strokeWidth;
	private int strokeColor;
	private int fillColor;
	private DrawingView drawingView;
	private Paint previewPaint;
	public Paint getPreviewPaint() {
		return previewPaint;
	}
	public void setPreviewPaint(Paint previewPaint) {
		this.previewPaint = previewPaint;
	}
	public Tool currentTool;
	

	public ToolBox(DrawingView drawingView) {
		super();
		this.setDrawingView(drawingView);	
		previewPaint = new Paint(); // Set the preview paint colour
		previewPaint.setStyle(Paint.Style.STROKE);
		previewPaint.setColor(Color.GRAY);
		previewPaint.setStrokeWidth(1);
		previewPaint.setStrokeCap(Paint.Cap.ROUND);
		previewPaint.setPathEffect(new DashPathEffect(new float[]{4.0f, 4.0f}, 1.0f));
	}
	public Tool getCurrentTool() {
		return currentTool;
	}
	public int getStrokeWidth() {
		return strokeWidth;
	}
	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	public int getStrokeColor() {
		return strokeColor;
	}
	public void setStrokeColor(int strokeColor) {
		this.strokeColor = strokeColor;
	}
	public int getFillColor() {
		return fillColor;
	}
	public void setFillColor(int fillColor) {
		this.fillColor = fillColor;
	}
	public ToolName getCurrentToolName() {
		// TODO Auto-generated method stub
		if ( currentTool == null)
			return ToolName.NONE;
		else
			return currentTool.getName();
	}
	public void changeTool(ToolName toolName) { // Uses Polymorphism to set the super class shape to the desired sub class
		switch (toolName)
		{
		case RECTANGLE:
			currentTool = new RectangleTool(this, toolName); break;
		case LINE: 
			currentTool = new LineTool(this, toolName); break;
		case ELLIPSE:
			currentTool = new EllipseTool(this, toolName); break;
		case CURVE:
			currentTool = new CurveTool(this, toolName); break;
		case PATH:
			currentTool = new PathTool(this, toolName); break;
			default:
				break;
		}
	}
	public DrawingView getDrawingView() {
		return drawingView;
	}
	public void setDrawingView(DrawingView drawingView) {
		this.drawingView = drawingView;
	}

}
