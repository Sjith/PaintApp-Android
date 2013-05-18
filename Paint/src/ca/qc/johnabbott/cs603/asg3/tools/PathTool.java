package ca.qc.johnabbott.cs603.asg3.tools;

import ca.qc.johnabbott.cs603.asg3.ToolBox;
import ca.qc.johnabbott.cs603.asg3.shapes.Paths;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.MotionEvent;

public class PathTool extends Tool {
	private Path path = new Path();
	public PathTool(ToolBox toolbox, ToolName name) {
		super(toolbox, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void touchStart(MotionEvent event) {
		if (preview)
		{
			path.moveTo(event.getX(), event.getY());
			toolbox.getDrawingView().invalidate();
		}
	}

	@Override
	public void touchEnd(MotionEvent event) {
		if (preview)
		{
			toolbox.currentTool.setPreview(false);	// user is done drawing so turn off preview mode and add drawing to shape array
			toolbox.getDrawingView().invalidate();
			addToDrawing();
			path = new Path();
		}
	}

	@Override
	public void touchMove(MotionEvent event) {
		if (preview)
		{
		path.lineTo(event.getX(), event.getY()); // Stores the mouse position in path
		toolbox.getDrawingView().invalidate();
		}
	}

	@Override
	public void drawPreview(Canvas canvas) {
		canvas.drawPath(path, toolbox.getPreviewPaint());
	}

	@Override
	public void addToDrawing() {
		toolbox.getDrawingView().addShapesToList(new Paths(toolbox.getStrokeColor(), toolbox.getFillColor(), toolbox.getStrokeWidth(), path));

	}

}
