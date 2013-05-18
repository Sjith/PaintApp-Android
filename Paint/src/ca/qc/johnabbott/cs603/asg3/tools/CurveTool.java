package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Paths;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.MotionEvent;

public class CurveTool extends Tool {
	private Path path = new Path();
	private Boolean curveState = false;
	private float firstX, firstY, lastX, lastY;
	public CurveTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void touchStart(MotionEvent event) {
		if (preview)
		{
			if (curveState) // Checks if its the 2nd click
			{
				path.setLastPoint(firstX, firstY); 
				path.quadTo(event.getX(), event.getY(), lastX, lastY);	
			}
			else
			{
				firstX = event.getX();
				firstY = event.getY();
				lastX = event.getX();
				lastY = event.getY();
			}
			toolbox.getDrawingView().invalidate();
			
		}

	}

	@Override
	public void touchEnd(MotionEvent event) {
		if (preview)
		{
			if (curveState)
			{
				path = new Path(); // user is done drawing so adds it to the list and preview mode is off now.
				path.setLastPoint(firstX, firstY);
				path.quadTo(event.getX(), event.getY(), lastX, lastY);
				toolbox.currentTool.setPreview(false);
				toolbox.getDrawingView().invalidate();
				addToDrawing();
				path = new Path();
				
			}
			else
			{
				lastX = event.getX();
				lastY = event.getY();
				//toolbox.getDrawingView().invalidate();
				curveState = true;
			}
			
		}

	}

	@Override
	public void touchMove(MotionEvent event) {
		if (preview)
		{		
			if ( curveState) // checks if we are still drawing the line or the curve
			{
				path = new Path(); // remakes the pat with the desired paramteters
				path.setLastPoint(firstX, firstY);
				path.quadTo(event.getX(), event.getY(), lastX, lastY);	
			}
			else
			{
				lastX = event.getX();
				lastY = event.getY();	
			}
			toolbox.getDrawingView().invalidate();
		}

	}

	@Override
	public void drawPreview(Canvas canvas) {
		if (curveState) // if its in first state draw the curve else draw a line
			canvas.drawPath(path, toolbox.getPreviewPaint());
		else
			canvas.drawLine(lastX, lastY,firstX, firstY, toolbox.getPreviewPaint());

	}

	@Override
	public void addToDrawing() {
		toolbox.getDrawingView().addShapesToList(new Paths(toolbox.getStrokeColor(), toolbox.getFillColor(), toolbox.getStrokeWidth(), path));
		curveState = false;
	}

}
